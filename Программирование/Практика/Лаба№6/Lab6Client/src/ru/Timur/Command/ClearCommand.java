package ru.Timur.Command;

import java.io.Serializable;

/**
 * Класс для инкапсуляции команды Clear
 * @author timur
 */
public class ClearCommand implements Command, Serializable {
    private final Validation validation;
    private static final long serialVersionUID = 1L;

    public ClearCommand(Validation validation) {
        this.validation = validation;
    }

    @Override
    public boolean execute() {
        return true;
    }

    @Override
    public void getDiscription(){
        System.out.println("Очищает коллекцию");
    }
}
