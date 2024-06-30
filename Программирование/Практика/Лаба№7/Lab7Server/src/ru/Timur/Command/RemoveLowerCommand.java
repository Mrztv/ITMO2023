package ru.Timur.Command;

import ru.Timur.Auth;
import ru.Timur.ClientData;
import ru.Timur.SpaceMarine;
import ru.Timur.User;

/**
 * Класс для инкапсуляции команды RemoveLower
 * @author timur
 */
public class RemoveLowerCommand implements Command{
    static final long serialVersionUID = 1L;
    private Storage storage;
    private User user;
    private ClientData data;

    private SpaceMarine spaceMarine;

    public RemoveLowerCommand(Storage storage) {
        this.storage = storage;
    }
    public ClientData getData() {
        return data;
    }

    public void setData(ClientData data) {
        this.data = data;
    }

    @Override
    public ClientData execute() {
        if(new Auth().inUsers(user.getName(), user.getPassword())){
            if (storage.removeLower(spaceMarine.getHealth(), user)) return new ClientData("Элемент удален");
            else return new ClientData("Элемент не удален");
        }
        return null;
    }

    public void setStorage(Storage storage){
        this.storage = storage;
    }
    @Override
    public String getDiscription() {
        return ("удалить из коллекции все элементы, меньшие, чем заданный");
    }
}
