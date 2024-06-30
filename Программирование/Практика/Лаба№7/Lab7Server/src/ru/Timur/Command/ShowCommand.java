package ru.Timur.Command;

import ru.Timur.Auth;
import ru.Timur.ClientData;
import ru.Timur.User;

/**
 * Класс для инкапсуляции команды Show
 * @author timur
 */
public class ShowCommand implements Command {
    private Storage storage;
    private User user;
    private ClientData data;
    private static final long serialVersionUID = 1L;

    public ShowCommand(Storage storage) {
        this.storage = storage;
    }

    public void setStorage(Storage storage){
        this.storage = storage;
    }
    @Override
    public ClientData execute() {
        if(new Auth().inUsers(user.getName(), user.getPassword())){
            return new ClientData(storage.show());
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
    public String getDiscription(){
        return ("Показывает содержимое коллекции");
    }
}
