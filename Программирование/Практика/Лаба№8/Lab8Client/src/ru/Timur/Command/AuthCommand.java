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

import ru.Timur.Exceptions.WrongPasswordExeption;
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
            //this.network = new Network(65125, InetAddress.getLocalHost());
            network = new Network(InetAddress.getLocalHost());
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
        } catch (IOException | WrongPasswordExeption e) {
            throw new RuntimeException(e);
        }
    }

    public void auth(String _login, String _password) throws IOException, WrongPasswordExeption {
        try {
            if(!_login.isEmpty() || !_login.isBlank()){
                login = _login;
            }

            if(!_password.isEmpty() || !_password.isBlank()){
                password = _password;
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            this.network.send(baos);
            User.getUser().setUser(this.login, this.password);
            this.network.recive();
        } catch (IOException | WrongPasswordExeption e) {
            throw e;
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
