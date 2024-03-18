package ru.Timur.Command;

import ru.Timur.Exceptions.NonValidFileElementException;
import ru.Timur.StreamReader;

import java.io.IOException;
import java.util.InputMismatchException;

/**
 * Класс для инкапсуляции команды Update
 * @author timur
 */
public class UpdateCommand implements Command {
    private final Storage storage;
    private final StreamReader streamReader;

    public UpdateCommand(Storage storage, StreamReader streamReader) {
        this.storage = storage;
        this.streamReader = streamReader;
    }
    @Override
    public void execute() {
        try{
            storage.update(streamReader);
        }catch (IOException | NonValidFileElementException | InputMismatchException e){
            System.out.println(e.toString());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void getDiscription(){
        System.out.println("Обновляет элемент по заданному ID");
    }

}
