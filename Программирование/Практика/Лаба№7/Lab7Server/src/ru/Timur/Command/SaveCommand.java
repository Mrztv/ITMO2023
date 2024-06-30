package ru.Timur.Command;

import ru.Timur.ClientData;

import java.io.File;

public class SaveCommand implements Command{
    private Storage storage;
    private  String pathFile;

    public SaveCommand(Storage storage, String pathFile) {
        this.storage = storage;
        this.pathFile = pathFile;
    }

    @Override
    public ClientData execute() {
        storage.save(new File(pathFile));
        return new ClientData("");
    }

    @Override
    public String getDiscription() {
        return "Сохраняет коллекцию в файл";
    }

    @Override
    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}
