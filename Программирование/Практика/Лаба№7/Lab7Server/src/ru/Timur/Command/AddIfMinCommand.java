package ru.Timur.Command;

import ru.Timur.ClientData;
import ru.Timur.SpaceMarine;

import java.io.IOException;
import java.io.Serializable;

/**
 * Класс для инкапсуляции команды AddIfMin
 * @author timur
 */
public class AddIfMinCommand implements Command {
    static final long serialVersionUID = 1L;
    private Storage storage;
    private SpaceMarine spaceMarine;

    public AddIfMinCommand(Storage storage){
        this.storage = storage;
    }

    @Override
    public ClientData execute() {
        if(storage.addIfMin(spaceMarine)) return new ClientData("Элемент добавлен");
        else return new ClientData("Не добавлен");
    }

    public void setStorage(Storage storage){
        this.storage = storage;
    }
    @Override
    public String getDiscription() {
        return ("Добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
    }
}
