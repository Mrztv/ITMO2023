package ru.Timur.Command;

import ru.Timur.ClientData;

/**
 * Класс для инкапсуляции команды FilterStartsWithName
 * @author timur
 */
public class FilterStartsWithNameCommand implements Command{
    static final long serialVersionUID = 1L;
    private  Storage storage;
    private String name;

    public FilterStartsWithNameCommand(Storage storage) {
        this.storage = storage;
    }

    @Override
    public ClientData execute() {
        return new ClientData(storage.filterStartsWithName(name));
    }

    public void setStorage(Storage storage){
        this.storage = storage;
    }
    @Override
    public String getDiscription() {
        return ("вывести элементы, значения поля name которых начинается с заданной подстроки");
    }
}
