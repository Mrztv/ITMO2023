package ru.Timur.Command;

public class FilterStartsWithNameCommand implements Command{
    private final Storage storage;

    public FilterStartsWithNameCommand(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void execute() {
        storage.filterStartsWithName();
    }

    @Override
    public void getDiscription() {
        System.out.println("вывести элементы, значения поля name которых начинается с заданной подстроки");
    }
}
