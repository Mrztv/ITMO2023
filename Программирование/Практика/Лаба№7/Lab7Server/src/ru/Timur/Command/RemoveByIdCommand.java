package ru.Timur.Command;

import ru.Timur.ClientData;

/**
 * Класс для инкапсуляции команды RemoveById
 * @author timur
 */
public class RemoveByIdCommand implements Command {
    static final long serialVersionUID = 1L;
    private Storage storage;
    private Long id;
    public RemoveByIdCommand(Storage storage) {
        this.storage = storage;
    }

    public void setId(Long id){
        this.id = id;
    }

    @Override
    public ClientData execute(){
        if (storage.removeById(id)){
            return new ClientData("Удалено");
        }else return new ClientData("Не удалено");
    }

    public void setStorage(Storage storage){
        this.storage = storage;
    }
    @Override
    public String getDiscription(){
        return ("Удаляет элемент с заданным Id");
    }
}
