package ru.Timur.Command;

import ru.Timur.ClientData;

/**
 * Класс для инкапсуляции команды Exit
 * @author timur
 */
public class ExitCommand implements Command {
    static final long serialVersionUID = 1L;
    private  Storage storage;

    public ExitCommand(Storage storage){
        this.storage = storage;
    }
    @Override
    public ClientData execute() {
        storage.exit();
        return new ClientData("...");
    }

    public void setStorage(Storage storage){
        this.storage = storage;
    }
    @Override
    public String getDiscription(){
        return ("Останавливает программу");
    }
}
