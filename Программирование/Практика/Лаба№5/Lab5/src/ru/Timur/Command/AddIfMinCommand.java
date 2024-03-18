package ru.Timur.Command;

import ru.Timur.StreamReader;

import java.io.IOException;
/**
 * Класс для инкапсуляции команды AddIfMin
 * @author timur
 */
public class AddIfMinCommand implements Command{
    private final Storage storage;
    private final StreamReader streamReader;

    public AddIfMinCommand(Storage storage, StreamReader streamReader){
        this.storage = storage;
        this.streamReader = streamReader;
    }

    @Override
    public void execute() {
        try{
            storage.addIfMin(streamReader);
        }catch (IOException e){
            System.out.println(e.toString());
        }
    }

    @Override
    public void getDiscription() {
        System.out.println("Добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
    }
}
