package ru.Timur.Command;

import org.graalvm.collections.Pair;
import ru.Timur.ClientData;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Класс для инкапсуляции команды ExecuteScript
 * @author timur
 */
public class ExecuteScriptCommand implements Command{
    static final long serialVersionUID = 1L;
    private Storage storage;

    private String filePath;

    private Queue<Command> queue;

    public ExecuteScriptCommand(Storage storage) {
        this.storage = storage;
        queue = new PriorityQueue<>();
    }

    public void setStorage(Storage storage){
        this.storage = storage;
    }
    @Override
    public ClientData execute() {
        storage.executeScript(queue);
        return new ClientData("Скрипт выполнен");
    }

    @Override
    public String getDiscription() {
        return ("Исполняет скрипт");
    }
}
