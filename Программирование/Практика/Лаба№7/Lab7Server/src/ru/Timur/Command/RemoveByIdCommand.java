package ru.Timur.Command;

import ru.Timur.Auth;
import ru.Timur.ClientData;
import ru.Timur.User;

/**
 * Класс для инкапсуляции команды RemoveById
 * @author timur
 */
public class RemoveByIdCommand implements Command {
    static final long serialVersionUID = 1L;
    private Storage storage;
    private User user;
    private Long id;
    private ClientData data;
    public RemoveByIdCommand(Storage storage) {
        this.storage = storage;
    }
    public ClientData getData() {
        return data;
    }

    public void setData(ClientData data) {
        this.data = data;
    }

    public void setId(Long id){
        this.id = id;
    }

    @Override
    public ClientData execute(){
        if(new Auth().inUsers(user.getName(), user.getPassword())){
            if (storage.removeById(id, user)) {
                return new ClientData("Удалено");
            } else return new ClientData("Не удалено");
        }
        return null;
    }

    public void setStorage(Storage storage){
        this.storage = storage;
    }
    @Override
    public String getDiscription(){
        return ("Удаляет элемент с заданным Id");
    }
}
