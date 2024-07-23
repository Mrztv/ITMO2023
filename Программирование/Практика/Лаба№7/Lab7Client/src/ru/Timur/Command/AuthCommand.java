//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ru.Timur.Command;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
import ru.Timur.Network;
import ru.Timur.User;

public class AuthCommand implements Command {
    private static final long serialVersionUID = 1L;
    private transient Network network;
    private static boolean isAuth = false;
    private String login = "";
    private String password = "";

    public AuthCommand() {
        try {
            this.network = new Network(9999, InetAddress.getLocalHost());
        } catch (UnknownHostException var2) {
            throw new RuntimeException(var2);
        }
    }

    public void auth() {
        try {
            Scanner scanner;
            for(scanner = new Scanner(System.in); this.login.isEmpty() || this.login.isBlank(); this.login = scanner.nextLine()) {
                System.out.println("Введите login");
            }

            while(this.password.isEmpty() || this.password.isBlank()) {
                System.out.println("Введите password");
                this.password = scanner.nextLine();
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            this.network.send(baos);
            User.getUser().setUser(this.login, this.password);
            this.network.recive();
        } catch (IOException var4) {
            throw new RuntimeException(var4);
        }
    }

    public boolean execute() {
        return false;
    }

    public void getDiscription() {
        System.out.println("Работает с юзером в бд");
    }

    public static boolean isAuth() {
        return isAuth;
    }

    public static void setIsAuth(boolean isAuth) {
        AuthCommand.isAuth = isAuth;
    }
}
