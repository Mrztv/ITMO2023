package ru.Timur.Command;

import ru.Timur.Auth;
import ru.Timur.ClientData;
import ru.Timur.User;

/**
 * Класс для инкапсуляции команды FilterStartsWithName
 * @author timur
 */
public class FilterStartsWithNameCommand implements Command{
    static final long serialVersionUID = 1L;
    private  Storage storage;
    private User user;
    private String name;
    private ClientData data;

    public FilterStartsWithNameCommand(Storage storage) {
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
            return new ClientData(storage.filterStartsWithName(name));

        }
        return null;
    }

    public void setStorage(Storage storage){
        this.storage = storage;
    }
    @Override
    public String getDiscription() {
        return ("вывести элементы, значения поля name которых начинается с заданной подстроки");
    }
}
