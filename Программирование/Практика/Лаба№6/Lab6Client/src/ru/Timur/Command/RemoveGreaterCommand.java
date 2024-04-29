package ru.Timur.Command;

import ru.Timur.SpaceMarine;
import ru.Timur.StreamReader;

import java.io.IOException;
import java.io.Serializable;

/**
 * Класс для инкапсуляции команды RemoveGreater
 * @author timur
 */
public class RemoveGreaterCommand implements Command, Serializable {
    Validation validation;
    static final long serialVersionUID = 1L;
    private SpaceMarine spaceMarine;
    public RemoveGreaterCommand(Validation validation){
        this.validation = validation;
    }

    @Override
    public boolean execute() {
        try {
            spaceMarine = validation.removeGreater();
            if(spaceMarine == null){
                throw new IOException();
            }
            return true;
        }catch (IOException e){
            System.out.println(e.toString());
            return false;
        }
    }

    @Override
    public void getDiscription() {
        System.out.println("удалить из коллекции все элементы, превышающий заданных");
    }
}
