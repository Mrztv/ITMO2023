package ru.Timur.Command;

import ru.Timur.Auth;
import ru.Timur.ClientData;
import ru.Timur.Exceptions.NonValidFileElementException;
import ru.Timur.SpaceMarine;
import ru.Timur.User;

import java.io.IOException;
import java.io.Serializable;
import java.util.InputMismatchException;
/**
 * Класс для инкапсуляции команды Add
 * @author timur
 */
public class AddCommand implements Command{
    static final long serialVersionUID = 1L;

    private ClientData data;


    private Storage storage;
    private User user;
    private SpaceMarine spaceMarine;

    public AddCommand(Storage storage){
        this.storage = storage;
    }

    @Override
    public String toString() {
        return "AddCommand{" +
                "validation=" + storage +
                ", spaceMarine=" + spaceMarine +
                '}';
    }

    @Override
    public ClientData execute() {
        try{
            if (new Auth().inUsers(user.getName(), user.getPassword())) {
                spaceMarine.setUser(user);
                if (storage.add(spaceMarine)) return new ClientData("Элемент добавлен");
                else return new ClientData("Не добавлен");
            } else return null;
        } catch (NonValidFileElementException e){
            return new ClientData("неверный логин или пароль");
        }
    }

    public void setStorage(Storage storage){
        this.storage = storage;
    }

    @Override
    public String getDiscription(){
        return ("Добавляет элемент в коллекцию");
    }

    public ClientData getData() {
        return data;
    }



    public void setData(ClientData data) {
        this.data = data;
    }


}
