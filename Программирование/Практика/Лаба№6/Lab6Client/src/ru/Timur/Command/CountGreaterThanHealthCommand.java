package ru.Timur.Command;

import ru.Timur.SpaceMarine;

/**
 * Класс для инкапсуляции команды CountGreaterThanHealth
 * @author timur
 */
public class CountGreaterThanHealthCommand implements Command{
    private final Validation validation;
    private static final long serialVersionUID = 1L;
    private Float health;
    public CountGreaterThanHealthCommand(Validation validation) {
        this.validation = validation;
    }

    @Override
    public boolean execute(){
        health = validation.countGreaterThanHealth();
        if(health != null) return true;
        else return false;
    }

    @Override
    public void getDiscription() {
        System.out.println("вывести количество элементов, значение поля health которых больше заданного");
    }
}
