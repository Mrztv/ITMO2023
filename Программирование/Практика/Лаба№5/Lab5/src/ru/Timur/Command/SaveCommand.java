package ru.Timur.Command;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;

public class SaveCommand implements Command{
    private final Storage storage;
    private final File file;

    public SaveCommand(Storage storage, File file){
        this.storage = storage;
        this.file = file;
    }

    @Override
    public void execute(){
        try{
            storage.save(file);
        }catch (FileNotFoundException | XMLStreamException e){
            System.out.println(e.toString());
        }
    }

    @Override
    public void getDiscription() {
        System.out.println("Сохранить коллекцию в файл");
    }
}
