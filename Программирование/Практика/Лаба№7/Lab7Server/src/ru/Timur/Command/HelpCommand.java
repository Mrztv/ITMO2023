package ru.Timur.Command;

import jdk.jshell.EvalException;
import ru.Timur.Auth;
import ru.Timur.ClientData;
import ru.Timur.User;

/**
 * Класс для инкапсуляции команды Help
 * @author timur
 */
public class HelpCommand implements Command {
    private Storage storage;
    static final long serialVersionUID = 1L;
    private User user;
    private ClientData data;

    public HelpCommand(Storage storage){
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
            return new ClientData(storage.help());
        }
        return null;
    }

    public void setStorage(Storage storage){
        this.storage = storage;
    }
    @Override
    public String getDiscription(){
        return  ("Выводит то, что делают команды");
    }
}
