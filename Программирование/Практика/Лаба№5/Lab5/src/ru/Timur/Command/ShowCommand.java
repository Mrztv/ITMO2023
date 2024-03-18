package ru.Timur.Command;
/**
 * Класс для инкапсуляции команды Show
 * @author timur
 */
public class ShowCommand implements Command {
    private final Storage storage;

    public ShowCommand(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void execute() {
        storage.show();
    }

    @Override
    public void getDiscription(){
        System.out.println("Показывает содержимое коллекции");
    }
}
