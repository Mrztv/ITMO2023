package ru.Timur.Command;

import ru.Timur.ClientData;
import ru.Timur.Pair;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.util.concurrent.RecursiveTask;

public class ForkJoinWrite extends RecursiveTask<DatagramChannel> {
    private SelectionKey key;
    public ForkJoinWrite(SelectionKey key) {
        this.key = key;
    }

    @Override
    protected DatagramChannel compute() {
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
                return (DatagramChannel) key.channel();
            }
        } catch (IOException e){
            System.out.println(e.toString());
        } catch (NullPointerException e){
            System.out.println(e.toString());
        }
        return null;
    }
}
