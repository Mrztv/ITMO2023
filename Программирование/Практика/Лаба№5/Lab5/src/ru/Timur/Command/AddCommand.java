package ru.Timur.Command;

import ru.Timur.Exceptions.NonValidFileElementException;
import ru.Timur.StreamReader;

import java.io.IOException;
import java.util.InputMismatchException;
/**
 * Класс для инкапсуляции команды Add
 * @author timur
 */
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
