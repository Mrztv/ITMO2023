package ru.Timur;

import ru.Timur.Command.DataBaseManager;
import ru.Timur.Command.Invoker;
import ru.Timur.Command.Storage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {

        Invoker invoker = new Invoker();
        invoker.main();
    }
}
