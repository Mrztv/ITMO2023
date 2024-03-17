package ru.Timur.Command;

public class AverageOfHealthCommand implements Command{
    private final Storage storage;

    public AverageOfHealthCommand(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void execute() {
        storage.averageOfHealth();
    }

    @Override
    public void getDiscription() {
        System.out.println("вывести среднее значение поля health для всех элементов коллекции");
    }
}
