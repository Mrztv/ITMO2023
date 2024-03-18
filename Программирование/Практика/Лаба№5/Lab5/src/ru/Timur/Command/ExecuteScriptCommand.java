package ru.Timur.Command;

/**
 * Класс для инкапсуляции команды ExecuteScript
 * @author timur
 */
public class ExecuteScriptCommand implements Command{
    private Storage storage;

    public ExecuteScriptCommand(Storage storage) {
        this.storage = storage;
    }


    @Override
    public void execute() {
        storage.execute_script();
    }

    @Override
    public void getDiscription() {
        System.out.println("Исполняет скрипт");
    }
}
