package ru.Timur.Command;

import java.io.Serializable;

/**
 * Интерфейс команд
 * @author timur
 */
public interface Command extends Serializable{
    static final long serialVersionUID = 1L;
    public boolean execute();
    public void getDiscription();

}
