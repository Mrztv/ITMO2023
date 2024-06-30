package ru.Timur;

import ru.Timur.Command.Invoker;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Invoker invoker = new Invoker();
        invoker.start();
        while(true){
            Scanner scanner = new Scanner(System.in);
            String com = scanner.nextLine();
            if(com.equals("exit")){
                invoker.interrupt();
                System.exit(1);
            } else if (com.equals("save")) {
                invoker.interrupt();
            }
        }
    }
}
