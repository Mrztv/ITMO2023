package ru.Timur.Command;

import ru.Timur.ClientData;

import java.io.Serializable;
import java.util.Set;

/**
 * Интерфейс команд
 * @author timur
 */
public interface Command extends Serializable{
    static final long serialVersionUID = 1L;
    public ClientData execute();
    public String getDiscription();
    public void setStorage(Storage storage);
}
