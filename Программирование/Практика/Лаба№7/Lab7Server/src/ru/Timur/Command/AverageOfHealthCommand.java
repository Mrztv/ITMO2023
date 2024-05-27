package ru.Timur.Command;

import ru.Timur.Auth;
import ru.Timur.ClientData;
import ru.Timur.User;

import java.io.Serializable;

/**
 * Класс для инкапсуляции команды AverageOfHealth
 * @author timur
 */
public class AverageOfHealthCommand implements Command {
    private Storage storage;
    private User user;
    private ClientData data;
    static final long serialVersionUID = 1L;

    public AverageOfHealthCommand(Storage storage) {
        this.storage = storage;
    }

    @Override
    public ClientData execute() {

        if(new Auth().inUsers(user.getName(), user.getPassword())) return new ClientData(storage.averageOfHealth());
        else return null;
    }

    public void setStorage(Storage storage){
        this.storage = storage;
    }
    @Override
    public String getDiscription() {
        return ("вывести среднее значение поля health для всех элементов коллекции");
    }
    public ClientData getData() {
        return data;
    }

    public void setData(ClientData data) {
        this.data = data;
    }
}
