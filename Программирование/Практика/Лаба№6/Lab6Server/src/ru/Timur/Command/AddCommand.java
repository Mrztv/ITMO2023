package ru.Timur.Command;

import ru.Timur.ClientData;
import ru.Timur.Exceptions.NonValidFileElementException;
import ru.Timur.SpaceMarine;

import java.io.IOException;
import java.io.Serializable;
import java.util.InputMismatchException;
/**
 * Класс для инкапсуляции команды Add
 * @author timur
 */
public class AddCommand implements Command{
    static final long serialVersionUID = 1L;
    private Storage storage;

    private SpaceMarine spaceMarine;

    public AddCommand(Storage storage){
        this.storage = storage;
    }

    @Override
    public String toString() {
        return "AddCommand{" +
                "validation=" + storage +
                ", spaceMarine=" + spaceMarine +
                '}';
    }

    @Override
    public ClientData execute() {
        if (storage.add(spaceMarine)) return new ClientData("Элемент добавлен");
        else return new ClientData("Не добавлен");
    }


    public void setStorage(Storage storage){
        this.storage = storage;
    }

    @Override
    public String getDiscription(){
        return ("Добавляет элемент в коллекцию");
    }



}
