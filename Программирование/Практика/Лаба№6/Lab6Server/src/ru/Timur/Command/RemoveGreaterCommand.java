package ru.Timur.Command;

import ru.Timur.ClientData;
import ru.Timur.SpaceMarine;

import java.io.IOException;
import java.io.Serializable;

/**
 * Класс для инкапсуляции команды RemoveGreater
 * @author timur
 */
public class RemoveGreaterCommand implements Command{
    private Storage storage;
    static final long serialVersionUID = 1L;
    private SpaceMarine spaceMarine;
    public RemoveGreaterCommand(Storage storage){
        this.storage = storage;
    }

    @Override
    public ClientData execute() {
        if(storage.removeGreater(spaceMarine.getHealth())) return new ClientData("Элемент удален");
        else return new ClientData("Элемент не удален");
    }

    public void setStorage(Storage storage){
        this.storage = storage;
    }
    @Override
    public String getDiscription() {
        return ("удалить из коллекции все элементы, превышающий заданных");
    }
}
