package ru.Timur.Command;

import ru.Timur.Auth;
import ru.Timur.ClientData;
import ru.Timur.User;

import java.io.File;

public class SaveCommand implements Command{
    private Storage storage;
    private  String pathFile;
    private User user;
    private ClientData data;

    public SaveCommand(Storage storage, String pathFile) {
        this.storage = storage;
        this.pathFile = pathFile;
    }

    @Override
    public ClientData execute() {
        if(new Auth().inUsers(user.getName(), user.getPassword())){
            storage.save(new File(pathFile));
            return new ClientData("");
        }
        return null;
    }

    public ClientData getData() {
        return data;
    }

    public void setData(ClientData data) {
        this.data = data;
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
