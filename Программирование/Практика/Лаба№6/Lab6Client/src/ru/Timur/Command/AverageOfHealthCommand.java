package ru.Timur.Command;

import java.io.Serializable;

/**
 * Класс для инкапсуляции команды AverageOfHealth
 * @author timur
 */
public class AverageOfHealthCommand implements Command, Serializable {
    private final Validation validation;
    private static final long serialVersionUID = 1L;

    public AverageOfHealthCommand(Validation validation) {
        this.validation = validation;
    }

    @Override
    public boolean execute() {
        return true;
    }

    @Override
    public void getDiscription() {
        System.out.println("вывести среднее значение поля health для всех элементов коллекции");
    }
}
