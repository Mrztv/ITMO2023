package ru.Timur.Command;

import ru.Timur.Auth;
import ru.Timur.ClientData;
import ru.Timur.User;

/**
 * Класс для инкапсуляции команды Exit
 * @author timur
 */
public class ExitCommand implements Command {
    static final long serialVersionUID = 1L;
    private  Storage storage;
    private User user;
    private ClientData data;

    public ExitCommand(Storage storage){
        this.storage = storage;
    }
    @Override
    public ClientData execute() {
        if(new Auth().inUsers(user.getName(), user.getPassword())){
            storage.exit();
            return new ClientData("...");
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
        return ("Останавливает программу");
    }
}
