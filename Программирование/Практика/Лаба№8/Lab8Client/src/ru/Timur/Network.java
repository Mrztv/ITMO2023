//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ru.Timur;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import ru.Timur.Command.AuthCommand;

public class Network {
    private int port;
    private InetAddress host;
    private DatagramChannel datagramChannel;
    private SocketAddress address;

    public Network(int port, InetAddress host) {
        try {
            this.datagramChannel = DatagramChannel.open();
            this.datagramChannel.configureBlocking(false);
        } catch (IOException var4) {
            System.out.println(var4.toString());
        }

        this.host = host;
        this.port = port;
        this.address = new InetSocketAddress(host, port);
    }

    public boolean send(ByteArrayOutputStream buffer) {
        try {
            ByteBuffer message = ByteBuffer.wrap(buffer.toByteArray());
            this.datagramChannel.send(message, this.address);
            return true;
        } catch (IOException var3) {
            System.out.println(var3.toString());
            return false;
        }
    }

    public boolean recive() {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(50000);

            while(buffer.position() == 0) {
                this.datagramChannel.receive(buffer);
            }

            ByteArrayInputStream bais = new ByteArrayInputStream(buffer.array());
            ObjectInputStream ois = new ObjectInputStream(bais);
            ClientData read = (ClientData)ois.readObject();
            read.write();
            if (!read.getOut().equals("Wrong password")) {
                AuthCommand.setIsAuth(true);
            }

            return true;
        } catch (IOException var5) {
            System.out.println(var5.toString());
            return false;
        } catch (ClassNotFoundException var6) {
            throw new RuntimeException(var6);
        }
    }
}
