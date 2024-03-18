package ru.Timur.Command;

import ru.Timur.StreamReader;

import java.io.IOException;
/**
 * Класс для инкапсуляции команды RemoveGreater
 * @author timur
 */
public class RemoveGreaterCommand implements Command{
    Storage storage;
    StreamReader streamReader;
    public RemoveGreaterCommand(Storage storage, StreamReader streamReader){
        this.storage = storage;
        this.streamReader = streamReader;
    }

    @Override
    public void execute() {
        try {
            storage.removeGreater(streamReader);
        }catch (IOException e){
            System.out.println(e.toString());
        }
    }

    @Override
    public void getDiscription() {
        System.out.println("удалить из коллекции все элементы, превышающий заданных");
    }
}
