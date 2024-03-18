package ru.Timur.Command;
/**
 * Класс для инкапсуляции команды CountGreaterThanHealth
 * @author timur
 */
public class CountGreaterThanHealthCommand implements Command{
    private final Storage storage;

    public CountGreaterThanHealthCommand(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void execute() {
        storage.countGreaterThanHealth();
    }

    @Override
    public void getDiscription() {
        System.out.println("вывести количество элементов, значение поля health которых больше заданного");
    }
}
