package ru.Timur.Command;

/**
 * Класс для инкапсуляции команды Help
 * @author timur
 */
public class HelpCommand implements Command {
    private final Storage storage;

    public HelpCommand(Storage storage){
        this.storage = storage;
    }


    @Override
    public void execute() {
        storage.help();
    }

    @Override
    public void getDiscription(){
        System.out.println("Выводит то, что делают команды");
    }
}
