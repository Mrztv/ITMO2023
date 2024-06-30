package ru.Timur.Command;

import ru.Timur.Auth;
import ru.Timur.ClientData;
import ru.Timur.User;

import java.io.Serializable;

/**
 * Класс для инкапсуляции команды Clear
 * @author timur
 */
public class ClearCommand implements Command {
    private  Storage storage;
    private ClientData data;
    static final long serialVersionUID = 1L;
    private User user;

    public ClearCommand(Storage storage) {
        this.storage = storage;
    }

    @Override
    public ClientData execute() {
        if(new Auth().inUsers(user.getName(), user.getPassword())){
            storage.clear(user);
            return new ClientData("Коллекция очищена");
        }
        return null;
    }
    public ClientData getData() {
        return data;
    }

    public void setData(ClientData data) {
        this.data = data;
    }

    public void setStorage(Storage storage){
        this.storage = storage;
    }
    @Override
    public String getDiscription(){
        return ("Очищает коллекцию");
    }
}
