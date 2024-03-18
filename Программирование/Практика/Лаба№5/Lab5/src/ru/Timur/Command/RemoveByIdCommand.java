package ru.Timur.Command;
/**
 * Класс для инкапсуляции команды RemoveById
 * @author timur
 */
public class RemoveByIdCommand implements Command {
    private final Storage storage;
    private int id;
    public RemoveByIdCommand(Storage storage) {
        this.storage = storage;
    }

    public void setId(int id){
        this.id = id;
    }

    @Override
    public void execute(){
        try{
            storage.remove_by_id();
        } catch (NumberFormatException e){
            throw e;
        }
    }

    @Override
    public void getDiscription(){
        System.out.println("Удаляет элемент с заданным Id");
    }
}
