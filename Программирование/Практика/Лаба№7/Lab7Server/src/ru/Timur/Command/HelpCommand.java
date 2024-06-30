package ru.Timur.Command;

import jdk.jshell.EvalException;
import ru.Timur.ClientData;

/**
 * Класс для инкапсуляции команды Help
 * @author timur
 */
public class HelpCommand implements Command {
    private Storage storage;
    static final long serialVersionUID = 1L;

    public HelpCommand(Storage storage){
        this.storage = storage;
    }


    @Override
    public ClientData execute() {
        return new ClientData(storage.help());
    }

    public void setStorage(Storage storage){
        this.storage = storage;
    }
    @Override
    public String getDiscription(){
        return  ("Выводит то, что делают команды");
    }
}
