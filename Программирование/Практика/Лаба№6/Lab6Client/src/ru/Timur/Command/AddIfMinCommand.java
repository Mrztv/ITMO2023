package ru.Timur.Command;

import ru.Timur.SpaceMarine;
import ru.Timur.StreamReader;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;

/**
 * Класс для инкапсуляции команды AddIfMin
 * @author timur
 */
public class AddIfMinCommand implements Command, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Validation validation;
    private SpaceMarine spaceMarine;

    public AddIfMinCommand(Validation validation){
        this.validation = validation;
    }

    @Override
    public boolean execute() {
        try{
            spaceMarine = validation.addIfMin();
            if(spaceMarine == null)
            {
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
        System.out.println("Добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
    }
}
