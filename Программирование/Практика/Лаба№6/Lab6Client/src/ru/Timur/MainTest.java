package ru.Timur;

import ru.Timur.Command.*;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Scanner;

public class MainTest {
    public static void main(String[] args) {
        try{
            while(true){
                String s;
                //Scanner sc = new Scanner(System.in);
                //s = sc.nextLine();
                //int length = s.length();

                Command command = new AddCommand(new Validation());
                command.execute();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(command);
                oos.close();
                baos.close();


                DatagramChannel dc;
                ByteBuffer buf = ByteBuffer.wrap(baos.toByteArray());

                InetAddress host;
                int port = 65125;
                SocketAddress address;
                host = InetAddress.getLocalHost();
                address = new InetSocketAddress(host, port);

                dc = DatagramChannel.open();
                dc.configureBlocking(false);

                dc.send(buf, address);
            }




        } catch (UnknownHostException e){
            System.out.println(e.toString());
        } catch (IOException e){
            System.out.println(e.toString());
        }


    }
}
