package ru.Timur.Command;

import java.io.*;
import java.net.*;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.sql.*;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.*;

import ru.Timur.*;

public class Invoker{

    private Storage storage = Storage.getStorage();

    public void main() {
        String pathFile = System.getenv("FileJAVA");
        Connection conn;
        //db connection block start
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/studs", "s409145", "IcZHJoli85q0w5VV");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //db connection block end

        //storage.add(new SpaceMarine("Timur", new Coordinates(123, 123.45), 123.3f, false, "sa", AstartesCategory.ASSAULT, new Chapter("name", 123L), "tima"));

        BufferedInputStream file = null;
        /*while (true){
            Scanner scanner;
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
                t.println(e.toString());
                scanner = new Scanner(System.in);
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
        }*/

        try {
            Selector selector = Selector.open();

            DatagramChannel dc = DatagramChannel.open();
            dc.configureBlocking(false);
            DatagramSocket ds = dc.socket();
            InetAddress host;
            int port = 65125;
            ds.bind(new InetSocketAddress(port));

            dc.register(selector, SelectionKey.OP_READ);


            ExecutorService executorService = Executors.newFixedThreadPool(20);


            Scanner exitScanner = new Scanner(System.in);

            while (true) {
                if(System.in.available() > 0){
                    String com = exitScanner.nextLine().trim();
                    if(com.equals("exit")){
                        System.exit(1);
                    } else if (com.equals("save")) {
                        storage.save(new File(pathFile));
                    }
                }
                selector.select();
                Set<SelectionKey> keys = selector.keys();
                Iterator<SelectionKey> iter = keys.iterator();

                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    if (key.isValid()) {


                        if (key.isReadable()) {
                            DatagramChannel datagramChannel;
                            ForkJoinPool forkJoinPool = new ForkJoinPool();
                            datagramChannel = forkJoinPool.invoke(new ForkJoinRead(key, executorService));
                            forkJoinPool.shutdown();
                        }



                        if (key.isWritable()){
                            DatagramChannel datagramChannel;
                            ForkJoinPool forkJoinPool = new ForkJoinPool();
                            datagramChannel = forkJoinPool.invoke(new ForkJoinWrite(key));
                            forkJoinPool.shutdown();


                        }


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