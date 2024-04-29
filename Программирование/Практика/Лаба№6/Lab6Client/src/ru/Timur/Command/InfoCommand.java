package ru.Timur.Command;
/**
 * Класс для инкапсуляции команды Info
 * @author timur
 */
public class InfoCommand implements Command {
    static final long serialVersionUID = 1L;
    private final Validation validation;

    public InfoCommand(Validation validation){
        this.validation = validation;
    }

    @Override
    public boolean execute(){
        return true;
    }

    @Override
    public void getDiscription(){
        System.out.println("Выводит информацию о коллекции");
    }
}
