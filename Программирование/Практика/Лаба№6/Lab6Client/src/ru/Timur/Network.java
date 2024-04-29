package ru.Timur;

import org.graalvm.nativeimage.Platform;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Selector;
import java.util.Base64;

public class Network {
    private int port;
    private InetAddress host;
    private DatagramChannel datagramChannel;

    private SocketAddress address;


    public Network(int port, InetAddress host) {
        try{
            datagramChannel = DatagramChannel.open();
            datagramChannel.configureBlocking(false);
        }catch (IOException e){
            System.out.println(e.toString());
        }

        this.host = host;
        this.port = port;
        address = new InetSocketAddress(host, port);
    }

    public boolean send(ByteArrayOutputStream buffer) {
        try{
            ByteBuffer message = ByteBuffer.wrap(buffer.toByteArray());

            datagramChannel.send(message, address);

            return true;

        } catch (IOException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public boolean recive(){
        try {
            ByteBuffer buffer = ByteBuffer.allocate(5000);

            while (buffer.position() == 0) {
                datagramChannel.receive(buffer);
            }

            ByteArrayInputStream bais = new ByteArrayInputStream(buffer.array());
            ObjectInputStream ois = new ObjectInputStream(bais);
            ClientData read = (ClientData) ois.readObject();
            read.write();
            return true;
        } catch (IOException e){
            System.out.println(e.toString());
            return false;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
