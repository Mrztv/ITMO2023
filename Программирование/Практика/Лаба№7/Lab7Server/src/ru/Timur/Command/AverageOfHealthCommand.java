package ru.Timur.Command;

import ru.Timur.ClientData;

import java.io.Serializable;

/**
 * Класс для инкапсуляции команды AverageOfHealth
 * @author timur
 */
public class AverageOfHealthCommand implements Command {
    private Storage storage;
    static final long serialVersionUID = 1L;

    public AverageOfHealthCommand(Storage storage) {
        this.storage = storage;
    }

    @Override
    public ClientData execute() {
        return new ClientData(storage.averageOfHealth());
    }

    public void setStorage(Storage storage){
        this.storage = storage;
    }
    @Override
    public String getDiscription() {
        return ("вывести среднее значение поля health для всех элементов коллекции");
    }
}
