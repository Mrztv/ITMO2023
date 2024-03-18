package ru.Timur.Command;

import ru.Timur.StreamReader;

import java.io.IOException;
/**
 * Класс для инкапсуляции команды RemoveLower
 * @author timur
 */
public class RemoveLowerCommand implements Command{
    private final Storage storage;
    private final StreamReader streamReader;

    public RemoveLowerCommand(Storage storage, StreamReader streamReader) {
        this.storage = storage;
        this.streamReader = streamReader;
    }

    @Override
    public void execute() {
        try{
            storage.removeLower(streamReader);
        }catch (IOException e){
            System.out.println(e.toString());
        }
    }

    @Override
    public void getDiscription() {
        System.out.println("удалить из коллекции все элементы, меньшие, чем заданный");
    }
}
