package ru.Timur.Command;

import ru.Timur.Auth;
import ru.Timur.ClientData;
import ru.Timur.User;

/**
 * Класс для инкапсуляции команды Info
 * @author timur
 */
public class InfoCommand implements Command {
    static final long serialVersionUID = 1L;
    private  Storage storage;
    private User user;
    private ClientData data;

    public InfoCommand(Storage storage){
        this.storage = storage;
    }
    public ClientData getData() {
        return data;
    }

    public void setData(ClientData data) {
        this.data = data;
    }

    @Override
    public ClientData execute(){

        if(new Auth().inUsers(user.getName(), user.getPassword())){
            return new ClientData(storage.info());
        }
        return null;
    }

    public void setStorage(Storage storage){
        this.storage = storage;
    }
    @Override
    public String getDiscription(){
        return ("Выводит информацию о коллекции");
    }
}
