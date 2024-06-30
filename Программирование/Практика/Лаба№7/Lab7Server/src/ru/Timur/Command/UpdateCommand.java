package ru.Timur.Command;

import ru.Timur.ClientData;
import ru.Timur.Exceptions.NonValidFileElementException;
import ru.Timur.SpaceMarine;

import java.io.IOException;
import java.util.InputMismatchException;

/**
 * Класс для инкапсуляции команды Update
 * @author timur
 */
public class UpdateCommand implements Command {
    static final long serialVersionUID = 1L;
    private Storage storage;
    private SpaceMarine spaceMarine;

    public UpdateCommand(Storage storage) {
        this.storage = storage;
    }
    @Override
    public ClientData execute() {
        if(storage.update(spaceMarine)) return new ClientData("Элемент обновлен");
        else return new ClientData("Элемент не обновлен");



    }

    public void setStorage(Storage storage){
        this.storage = storage;
    }
    @Override
    public String  getDiscription(){
        return ("Обновляет элемент по заданному ID");
    }

}
