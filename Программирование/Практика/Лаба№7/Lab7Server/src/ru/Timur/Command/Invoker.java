package ru.Timur.Command;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import com.sun.tools.javac.Main;
import ru.Timur.Pair;
import ru.Timur.ClientData;
import ru.Timur.SpaceMarine;
import ru.Timur.XML.StaxXMLReader;

import ru.Timur.Command.*;
import ru.Timur.Exceptions.CantReadFileException;
import ru.Timur.Exceptions.EndOfFileException;
import ru.Timur.Exceptions.NonValidFileElementException;

import javax.xml.stream.XMLStreamException;

public class Invoker extends Thread{

    private static Storage storage = new Storage();

    @Override
    public void run() {
        String pathFile = System.getenv("FileJAVA");

        BufferedInputStream file = null;
        while (true){
            try{
                if(Files.exists(Paths.get(pathFile))) {
                    if(Files.isReadable(Paths.get(pathFile))){
                        file = new BufferedInputStream(new FileInputStream(pathFile));
                        break;
                    }else{
                        throw new CantReadFileException();
                    }
                }else {
                    throw new FileNotFoundException();
                }
            }catch (FileNotFoundException | NullPointerException | CantReadFileException e){
                System.out.println(e.toString());
                Scanner scanner = new Scanner(System.in);
                System.out.println("""
                        Выберите что делать:
                        1) Ввести путь к файлу вручную
                        !1) Остановка программы""");
                if(scanner.nextLine().equals("1")){
                    pathFile = scanner.nextLine();
                    scanner.close();
                }
                else {
                    return;
                }
            }
        }

        try{
            StaxXMLReader processor = new StaxXMLReader(file);
            while(processor.hasNext()){
                SpaceMarine element;
                try {
                    element = processor.readElement();
                }catch (EndOfFileException e){
                    break;
                }
                //System.out.println(string);
                storage.setInputStream(file);
                try{
                    storage.add(element);
                }catch (NonValidFileElementException e){
                    System.out.println(e.toString());
                }
            }
        }catch (XMLStreamException e){
            System.out.println(e.toString());
        }

        try {
            Selector selector = Selector.open();

            DatagramChannel dc = DatagramChannel.open();
            dc.configureBlocking(false);
            DatagramSocket ds = dc.socket();
            InetAddress host;
            int port = 65125;
            ds.bind(new InetSocketAddress(port));

            dc.register(selector, SelectionKey.OP_READ);

            while (true) {
                selector.select();
                Set<SelectionKey> keys = selector.keys();
                Iterator<SelectionKey> iter = keys.iterator();

                while (iter.hasNext()) {
                    SelectionKey key = iter.next();

                    if (key.isValid()) {


                        if (key.isReadable()) {
                            try {

                                DatagramChannel datagramChannel = (DatagramChannel) key.channel();
                                ByteBuffer bf = ByteBuffer.allocate(4000);

                                InetSocketAddress address = (InetSocketAddress)datagramChannel.receive(bf);

                                if (bf.position() != 0) {
                                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bf.array());
                                    ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                                    Command command = (Command) objectInputStream.readObject();
                                    Pair<ClientData, InetSocketAddress> attachPair = new Pair<>();
                                    if (command != null) {
                                        command.setStorage(storage);
                                        ClientData data = command.execute();
                                        attachPair.create(data, address);
                                    }

                                    datagramChannel.register(key.selector(), SelectionKey.OP_WRITE).attach(attachPair);
                                }

                            } catch (IOException e) {
                                System.out.println(e.toString());
                            } catch (ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        }



                        if (key.isWritable()){
                            try{
                                Pair<ClientData, InetSocketAddress> attachPair = (Pair<ClientData, InetSocketAddress>) key.attachment();
                                if(attachPair != null){

                                    ClientData data = attachPair.getLeft();
                                    InetSocketAddress address = attachPair.getRight();

                                    byte[] buf = new byte[5000];
                                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                    ObjectOutputStream oos = new ObjectOutputStream(baos);
                                    oos.writeObject(data);
                                    buf = baos.toByteArray();

                                    DatagramSocket datagramSocket = new DatagramSocket();
                                    DatagramPacket dp = new DatagramPacket(buf, buf.length, address.getAddress(), address.getPort());
                                    datagramSocket.send(dp);
                                    key.channel().register(key.selector(), SelectionKey.OP_READ);
                                }
                            } catch (IOException e){
                                System.out.println(e.toString());
                            } catch (NullPointerException e){
                                System.out.println(e.toString());
                            }
                        }
                    }try {
                        Thread.sleep(500 );
                    } catch (InterruptedException e) {
                        String command = e.getMessage();
                        storage.save(new File(System.getenv("FileJAVA")));
                    }
                }

            }
        } catch (SocketException ex) {
            throw new RuntimeException(ex);
        } catch (ClosedChannelException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


    }



}