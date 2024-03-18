package ru.Timur.Command;
/**
 * Класс для инкапсуляции команды Info
 * @author timur
 */
public class InfoCommand implements Command {
    private final Storage storage;

    public InfoCommand(Storage storage){
        this.storage = storage;
    }

    @Override
    public void execute(){
        storage.info();
    }

    @Override
    public void getDiscription(){
        System.out.println("Выводит информацию о коллекции");
    }
}
