package ru.Timur.Command;

import ru.Timur.Auth;
import ru.Timur.ClientData;
import ru.Timur.Exceptions.NonValidFileElementException;
import ru.Timur.SpaceMarine;
import ru.Timur.User;

import java.io.IOException;
import java.util.InputMismatchException;

/**
 * Класс для инкапсуляции команды Update
 * @author timur
 */
public class UpdateCommand implements Command {
    static final long serialVersionUID = 1L;
    private Storage storage;
    private User user;
    private ClientData data;
    private SpaceMarine spaceMarine;

    public UpdateCommand(Storage storage) {
        this.storage = storage;
    }
    @Override
    public ClientData execute() {
        if(new Auth().inUsers(user.getName(), user.getPassword())){
            if (storage.update(spaceMarine, user)) return new ClientData("Элемент обновлен");
            else return new ClientData("Элемент не обновлен");
        }
        return null;
    }
    public ClientData getData() {
        return data;
    }

    public void setData(ClientData data) {
        this.data = data;
    }

    public void setStorage(Storage storage){
        this.storage = storage;
    }
    @Override
    public String  getDiscription(){
        return ("Обновляет элемент по заданному ID");
    }

}
