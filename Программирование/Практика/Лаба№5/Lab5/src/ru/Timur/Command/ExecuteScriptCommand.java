package ru.Timur.Command;

import java.io.FileNotFoundException;

public class ExecuteScriptCommand implements Command{
    private Storage storage;

    public ExecuteScriptCommand(Storage storage) {
        this.storage = storage;
    }


    @Override
    public void execute() {
        storage.execute_script();
    }

    @Override
    public void getDiscription() {
        System.out.println("Исполняет скрипт");
    }
}
