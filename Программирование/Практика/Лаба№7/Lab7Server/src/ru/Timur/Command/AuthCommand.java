package ru.Timur.Command;

import ru.Timur.Auth;
import ru.Timur.ClientData;
import ru.Timur.Exceptions.NonValidFileElementException;
import ru.Timur.Exceptions.WrongPasswordExeption;
import ru.Timur.SpaceMarine;
import ru.Timur.User;

import java.io.IOException;
import java.io.Serializable;
import java.util.InputMismatchException;
/**
 * Класс для инкапсуляции команды Add
 * @author timur
 */
public class AuthCommand implements Command{
    static final long serialVersionUID = 1L;

    private ClientData data;

    private String login;
    private String password;


    private Storage storage;


    public AuthCommand(Storage storage){
        this.storage = storage;
    }

    @Override
    public String toString() {
        return "AuthCommand{" +
                "validation=" + storage +
                '}';
    }

    @Override
    public ClientData execute() {
        try{

            Auth auth = new Auth();
            if(auth.inUsers(login, password)){
                return new ClientData("Вы вошли");
            }
            else{
                auth.createNewUser(login, password);
                return new ClientData("Юзер создан");
            }
        } catch (WrongPasswordExeption e){
            return new ClientData("Wrong password");
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
