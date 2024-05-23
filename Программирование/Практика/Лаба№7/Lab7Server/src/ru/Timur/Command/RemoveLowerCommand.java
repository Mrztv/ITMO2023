package ru.Timur.Command;

import ru.Timur.ClientData;
import ru.Timur.SpaceMarine;

/**
 * Класс для инкапсуляции команды RemoveLower
 * @author timur
 */
public class RemoveLowerCommand implements Command{
    static final long serialVersionUID = 1L;
    private Storage storage;

    private SpaceMarine spaceMarine;

    public RemoveLowerCommand(Storage storage) {
        this.storage = storage;
    }

    @Override
    public ClientData execute() {
        if(storage.removeLower(spaceMarine.getHealth())) return new ClientData("Элемент удален");
        else return new ClientData("Элемент не удален");
    }

    public void setStorage(Storage storage){
        this.storage = storage;
    }
    @Override
    public String getDiscription() {
        return ("удалить из коллекции все элементы, меньшие, чем заданный");
    }
}
