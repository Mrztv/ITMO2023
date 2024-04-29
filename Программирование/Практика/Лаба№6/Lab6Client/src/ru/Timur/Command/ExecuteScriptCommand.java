package ru.Timur.Command;

import org.graalvm.collections.Pair;
import ru.Timur.ExecuteQueue;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Класс для инкапсуляции команды ExecuteScript
 * @author timur
 */
public class ExecuteScriptCommand implements Command{
    private Validation validation;
    private static final long serialVersionUID = 1L;

    private String filePath;

    private Queue<Command> queue;

    public ExecuteScriptCommand(Validation validation) {
        this.validation = validation;
        queue = new PriorityQueue<>();
    }

    @Override
    public boolean execute() {
        filePath = validation.execute_script();
        queue = ExecuteQueue.getQueue();
        ExecuteQueue.clear();
        return true;
    }

    @Override
    public void getDiscription() {
        System.out.println("Исполняет скрипт");
    }
}
