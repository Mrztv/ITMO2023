package ru.Timur.Command;
/**
 * Класс для инкапсуляции команды FilterStartsWithName
 * @author timur
 */
public class FilterStartsWithNameCommand implements Command{
    static final long serialVersionUID = 1L;
    private final Validation validation;
    private String name;

    public FilterStartsWithNameCommand(Validation validation) {
        this.validation = validation;
    }

    @Override
    public boolean execute() {
        name = validation.filterStartsWithName();
        return true;
    }

    @Override
    public void getDiscription() {
        System.out.println("вывести элементы, значения поля name которых начинается с заданной подстроки");
    }
}
