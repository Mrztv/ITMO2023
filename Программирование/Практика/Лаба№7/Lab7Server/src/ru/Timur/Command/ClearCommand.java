package ru.Timur.Command;

import ru.Timur.ClientData;

import java.io.Serializable;

/**
 * Класс для инкапсуляции команды Clear
 * @author timur
 */
public class ClearCommand implements Command {
    private  Storage storage;
    static final long serialVersionUID = 1L;

    public ClearCommand(Storage storage) {
        this.storage = storage;
    }

    @Override
    public ClientData execute() {
        storage.clear();
        return new ClientData("Коллекция очищена");
    }

    public void setStorage(Storage storage){
        this.storage = storage;
    }
    @Override
    public String getDiscription(){
        return ("Очищает коллекцию");
    }
}
