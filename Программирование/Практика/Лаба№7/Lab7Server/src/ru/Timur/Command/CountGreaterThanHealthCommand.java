package ru.Timur.Command;

import ru.Timur.ClientData;
import ru.Timur.SpaceMarine;

/**
 * Класс для инкапсуляции команды CountGreaterThanHealth
 * @author timur
 */
public class CountGreaterThanHealthCommand implements Command{
    private  Storage storage;
    private Float health;
    static final long serialVersionUID = 1L;

    public CountGreaterThanHealthCommand(Storage storage) {
        this.storage = storage;
    }

    @Override
    public ClientData execute() {
        return new ClientData(storage.countGreaterThanHealth(health));
    }


    public void setStorage(Storage storage){
        this.storage = storage;
    }

    @Override
    public String getDiscription() {
        return ("вывести количество элементов, значение поля health которых больше заданного");
    }
}
