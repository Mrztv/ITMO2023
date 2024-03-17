package ru.Timur.Command;

import ru.Timur.Command.Command;
import ru.Timur.Command.Storage;

public class ExitCommand implements Command {
    private final Storage storage;

    public ExitCommand(Storage storage){
        this.storage = storage;
    }
    @Override
    public void execute() {
        storage.exit();
    }

    @Override
    public void getDiscription(){
        System.out.println("Останавливает программу");
    }
}
