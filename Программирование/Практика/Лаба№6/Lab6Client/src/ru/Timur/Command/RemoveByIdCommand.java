package ru.Timur.Command;

import java.io.IOException;

/**
 * Класс для инкапсуляции команды RemoveById
 * @author timur
 */
public class RemoveByIdCommand implements Command {
    static final long serialVersionUID = 1L;
    private final Validation validation;
    private Long id;
    public RemoveByIdCommand(Validation validation) {
        this.validation = validation;
    }


    @Override
    public boolean execute(){
        id = validation.remove_by_id();
        if(id != null){
            return  true;
        }
        else return false;
    }

    @Override
    public void getDiscription(){
        System.out.println("Удаляет элемент с заданным Id");
    }
}
