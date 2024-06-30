package ru.Timur.Command;

import ru.Timur.Auth;
import ru.Timur.ClientData;
import ru.Timur.SpaceMarine;
import ru.Timur.User;

import java.io.IOException;
import java.io.Serializable;

/**
 * Класс для инкапсуляции команды AddIfMin
 * @author timur
 */
public class AddIfMinCommand implements Command {
    static final long serialVersionUID = 1L;
    private User user;
    private Storage storage;
    private ClientData data;
    private SpaceMarine spaceMarine;

    public AddIfMinCommand(Storage storage){
        this.storage = storage;
    }

    @Override
    public ClientData execute() {

        if(new Auth().inUsers(user.getName(), user.getPassword())){
            if (storage.addIfMin(spaceMarine)) return new ClientData("Элемент добавлен");
            else return new ClientData("Не добавлен");
        }
        return null;
    }

    public void setStorage(Storage storage){
        this.storage = storage;
    }
    @Override
    public String getDiscription() {
        return ("Добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
    }
    public ClientData getData() {
        return data;
    }

    public void setData(ClientData data) {
        this.data = data;
    }
}
