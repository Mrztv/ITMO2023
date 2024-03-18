package ru.Timur.Command;

import ru.Timur.Command.Command;
/**
 * Класс для инкапсуляции команды Clear
 * @author timur
 */
public class ClearCommand implements Command {
    private final Storage storage;

    public ClearCommand(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void execute() {
        storage.clear();
    }

    @Override
    public void getDiscription(){
        System.out.println("Очищает коллекцию");
    }
}
