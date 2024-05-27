package ru.Timur.Command;

import ru.Timur.Auth;
import ru.Timur.ClientData;
import ru.Timur.SpaceMarine;
import ru.Timur.User;

import java.io.IOException;
import java.io.Serializable;

/**
 * Класс для инкапсуляции команды RemoveGreater
 * @author timur
 */
public class RemoveGreaterCommand implements Command{
    private Storage storage;
    private User user;
    private ClientData data;
    static final long serialVersionUID = 1L;
    private SpaceMarine spaceMarine;
    public RemoveGreaterCommand(Storage storage){
        this.storage = storage;
    }
    public ClientData getData() {
        return data;
    }

    public void setData(ClientData data) {
        this.data = data;
    }

    @Override
    public ClientData execute() {
        if(new Auth().inUsers(user.getName(), user.getPassword())){
            if(storage.removeGreater(spaceMarine.getHealth(), user)) return new ClientData("Элемент удален");
            else return new ClientData("Элемент не удален");

        }
        return null;
    }

    public void setStorage(Storage storage){
        this.storage = storage;
    }
    @Override
    public String getDiscription() {
        return ("удалить из коллекции все элементы, превышающий заданных");
    }
}
