package ru.Timur.Command;

import ru.Timur.ClientData;

/**
 * Класс для инкапсуляции команды Show
 * @author timur
 */
public class ShowCommand implements Command {
    private Storage storage;
    private static final long serialVersionUID = 1L;

    public ShowCommand(Storage storage) {
        this.storage = storage;
    }

    public void setStorage(Storage storage){
        this.storage = storage;
    }
    @Override
    public ClientData execute() {
        return new ClientData(storage.show());
    }

    @Override
    public String getDiscription(){
        return ("Показывает содержимое коллекции");
    }
}
