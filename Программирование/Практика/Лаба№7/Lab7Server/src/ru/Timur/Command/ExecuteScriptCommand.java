package ru.Timur.Command;

import org.graalvm.collections.Pair;
import ru.Timur.Auth;
import ru.Timur.ClientData;
import ru.Timur.User;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Класс для инкапсуляции команды ExecuteScript
 * @author timur
 */
public class ExecuteScriptCommand implements Command{
    static final long serialVersionUID = 1L;
    private Storage storage;
    private User user;

    private String filePath;
    private ClientData data;

    private Queue<Command> queue;

    public ExecuteScriptCommand(Storage storage) {
        this.storage = storage;
        queue = new PriorityQueue<>();
    }
    public ClientData getData() {
        return data;
    }

    public void setData(ClientData data) {
        this.data = data;
    }

    public void setStorage(Storage storage){
        this.storage = storage;
    }
    @Override
    public ClientData execute() {
        if(new Auth().inUsers(user.getName(), user.getPassword())){

            storage.executeScript(queue);
            return new ClientData("Скрипт выполнен");
        }
        return null;
    }

    @Override
    public String getDiscription() {
        return ("Исполняет скрипт");
    }
}
