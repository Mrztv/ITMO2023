package ru.Timur.Command;

import ru.Timur.SpaceMarine;
import ru.Timur.StreamReader;

import java.io.IOException;
/**
 * Класс для инкапсуляции команды RemoveLower
 * @author timur
 */
public class RemoveLowerCommand implements Command{
    static final long serialVersionUID = 1L;
    private final Validation validation;

    private SpaceMarine spaceMarine;

    public RemoveLowerCommand(Validation validation) {
        this.validation = validation;
    }

    @Override
    public boolean execute() {
        spaceMarine = validation.removeLower();
        return spaceMarine != null;
    }

    @Override
    public void getDiscription() {
        System.out.println("удалить из коллекции все элементы, меньшие, чем заданный");
    }
}
