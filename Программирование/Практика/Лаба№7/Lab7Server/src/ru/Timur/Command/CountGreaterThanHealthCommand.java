package ru.Timur.Command;

import ru.Timur.Auth;
import ru.Timur.ClientData;
import ru.Timur.SpaceMarine;
import ru.Timur.User;

/**
 * Класс для инкапсуляции команды CountGreaterThanHealth
 * @author timur
 */
public class CountGreaterThanHealthCommand implements Command{
    private  Storage storage;
    private Float health;
    private User user;
    private ClientData data;
    static final long serialVersionUID = 1L;

    public CountGreaterThanHealthCommand(Storage storage) {
        this.storage = storage;
    }

    @Override
    public ClientData execute() {
        if(new Auth().inUsers(user.getName(), user.getPassword())){
            return new ClientData(storage.countGreaterThanHealth(health));
        }
        return null;
    }


    public void setStorage(Storage storage){
        this.storage = storage;
    }

    @Override
    public String getDiscription() {
        return ("вывести количество элементов, значение поля health которых больше заданного");
    }
    public ClientData getData() {
        return data;
    }

    public void setData(ClientData data) {
        this.data = data;
    }
}
