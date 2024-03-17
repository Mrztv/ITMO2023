package ru.Timur.Command;

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
