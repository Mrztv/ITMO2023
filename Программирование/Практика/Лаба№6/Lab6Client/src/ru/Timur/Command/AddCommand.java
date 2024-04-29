package ru.Timur.Command;

import ru.Timur.Exceptions.NonValidFileElementException;
import ru.Timur.SpaceMarine;
import ru.Timur.StreamReader;

import java.io.IOException;
import java.io.Serializable;
import java.util.InputMismatchException;
/**
 * Класс для инкапсуляции команды Add
 * @author timur
 */
public class AddCommand implements Command, Serializable  {
    static final long serialVersionUID = 1L;
    private Validation validation;

    private SpaceMarine spaceMarine;

    public AddCommand(Validation validation){
        this.validation = validation;
    }

    @Override
    public String toString() {
        return "AddCommand{" +
                "validation=" + validation +
                ", spaceMarine=" + spaceMarine +
                '}';
    }

    @Override
    public boolean execute() {
        try{
            spaceMarine = validation.add();
            if(spaceMarine == null){
                throw new IOException();
            }
            return true;
        }catch (IOException | NonValidFileElementException | InputMismatchException e){
            System.out.println(e.toString());
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public void getDiscription(){
        System.out.println("Добавляет элемент в коллекцию");
    }



}
