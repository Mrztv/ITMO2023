package ru.Timur.Command;

import ru.Timur.Exceptions.NonValidFileElementException;
import ru.Timur.SpaceMarine;
import ru.Timur.StreamReader;

import java.io.IOException;
import java.util.InputMismatchException;

/**
 * Класс для инкапсуляции команды Update
 * @author timur
 */
public class UpdateCommand implements Command {
    static final long serialVersionUID = 1L;
    private final Validation validation;
    private SpaceMarine spaceMarine;

    public UpdateCommand(Validation validation) {
        this.validation = validation;
    }
    @Override
    public boolean execute() {
        try{
            spaceMarine = validation.update();
            if(spaceMarine != null){
                return true;
            }
            return false;
        }catch (IOException | NonValidFileElementException | InputMismatchException e){
            System.out.println(e.toString());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void getDiscription(){
        System.out.println("Обновляет элемент по заданному ID");
    }

}
