//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ru.Timur;

import ru.Timur.Command.AuthCommand;
import ru.Timur.Command.Invoker;
import ru.Timur.Command.Validation;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        System.out.println(System.getenv("PATH_TO_FX"));



        while(!AuthCommand.isAuth()) {
            AuthCommand authCommand = new AuthCommand();
            authCommand.auth();
        }

        while(true) {
            try {
                Invoker invoker = new Invoker(System.in);
                invoker.readStream(new Validation());
            } catch (RuntimeException var2) {
                System.out.println(var2.toString());
            }
        }
    }
}
