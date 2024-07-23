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
import java.nio.file.Paths;

import javafx.fxml.FXMLLoader;
import ru.Timur.Command.AuthCommand;
import ru.Timur.Exceptions.WrongPasswordExeption;

public class Network {
    private int port = 9999;
    private InetAddress host;
    private DatagramChannel datagramChannel;
    private SocketAddress address;

    public Network(InetAddress host) {
        try {
            this.datagramChannel = DatagramChannel.open();
            this.datagramChannel.configureBlocking(false);
        } catch (IOException var4) {
            System.out.println(var4.toString());
        }

        this.host = host;
        this.address = new InetSocketAddress(host, port);
    }

    public boolean send(ByteArrayOutputStream buffer) {
        try {
            ByteBuffer message = ByteBuffer.wrap(buffer.toByteArray());
            this.datagramChannel.send(message, this.address);
            return true;
        } catch (IOException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public boolean recive() throws WrongPasswordExeption {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(Paths.get("./src/resource/ru/Timur/LoginWindow.fxml").toUri().toURL());


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
            else throw new WrongPasswordExeption(read.getOut().toString());

            return true;
        } catch (IOException e) {
            return false;
        } catch (ClassNotFoundException var6) {
            throw new RuntimeException(var6);
        }
    }

    public ClientData getAnswer() throws WrongPasswordExeption {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(Paths.get("./src/resource/ru/Timur/LoginWindow.fxml").toUri().toURL());


            ByteBuffer buffer = ByteBuffer.allocate(50000);

            while(buffer.position() == 0) {
                this.datagramChannel.receive(buffer);
            }

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer.array());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (ClientData)objectInputStream.readObject();
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException var6) {
            throw new RuntimeException(var6);
        }
    }

}
