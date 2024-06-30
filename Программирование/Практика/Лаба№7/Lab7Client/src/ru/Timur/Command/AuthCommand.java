package ru.Timur.Command;

import ru.Timur.Network;
import ru.Timur.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class AuthCommand implements Command {
    private static final long serialVersionUID = 1L;
    private transient Network network;

    private static boolean isAuth = false;

    private String login = "";
    private String password = "";



    public AuthCommand(){
        try {
            network = new Network(65125, InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public void auth(){
        try{

            Scanner scanner = new Scanner(System.in);
            while(login.isEmpty() || login.isBlank()){
                System.out.println("Введите login");
                login = scanner.nextLine();
            }

            while(password.isEmpty() || password.isBlank()){
                System.out.println("Введите password");
                password = scanner.nextLine();
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            network.send(baos);
            User.getUser().setUser(login, password);

            network.recive();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean execute() {
        return false;
    }

    @Override
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
