package ru.Timur.Command;

import ru.Timur.ClientData;

/**
 * Класс для инкапсуляции команды Info
 * @author timur
 */
public class InfoCommand implements Command {
    static final long serialVersionUID = 1L;
    private  Storage storage;

    public InfoCommand(Storage storage){
        this.storage = storage;
    }

    @Override
    public ClientData execute(){
        return new ClientData(storage.info());
    }

    public void setStorage(Storage storage){
        this.storage = storage;
    }
    @Override
    public String getDiscription(){
        return ("Выводит информацию о коллекции");
    }
}
