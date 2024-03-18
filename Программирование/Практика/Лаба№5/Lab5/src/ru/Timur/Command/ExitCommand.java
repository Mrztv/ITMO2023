package ru.Timur.Command;

/**
 * Класс для инкапсуляции команды Exit
 * @author timur
 */
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
