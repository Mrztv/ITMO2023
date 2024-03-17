package ru.Timur.Command;

import ru.Timur.Exceptions.ExitException;
import ru.Timur.Exceptions.NonValidFileElementException;
import ru.Timur.Scripts.StreamReader;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Objects;

public class AddCommand implements Command {
    private Storage storage;
    private StreamReader streamReader;

    public AddCommand(Storage storage, StreamReader streamReader){
        this.storage = storage;
        this.streamReader = streamReader;
    }

    @Override
    public void execute() {
        try{
            storage.add(streamReader);
        }catch (IOException | NonValidFileElementException | InputMismatchException e){
            System.out.println(e.toString());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void getDiscription(){
        System.out.println("Добавляет элемент в коллекцию");
    }

}
