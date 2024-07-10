//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ru.Timur.Command;

import java.util.PriorityQueue;
import java.util.Queue;
import ru.Timur.ExecuteQueue;
import ru.Timur.User;

public class ExecuteScriptCommand implements Command {
    private Validation validation;
    private User user = User.getUser();
    private static final long serialVersionUID = 1L;
    private String filePath;
    private Queue<Command> queue;

    public ExecuteScriptCommand(Validation validation) {
        this.validation = validation;
        this.queue = new PriorityQueue();
    }

    public boolean execute() {
        this.user = User.getUser();
        this.filePath = this.validation.execute_script();
        this.queue = ExecuteQueue.getQueue();
        ExecuteQueue.clear();
        return true;
    }

    public void getDiscription() {
        System.out.println("Исполняет скрипт");
    }
}
