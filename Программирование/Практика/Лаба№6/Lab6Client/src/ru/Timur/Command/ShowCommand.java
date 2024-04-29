package ru.Timur.Command;
/**
 * Класс для инкапсуляции команды Show
 * @author timur
 */
public class ShowCommand implements Command {
    private transient final Validation validation;
    private static final long serialVersionUID = 1L;

    public ShowCommand(Validation validation) {
        this.validation = validation;
    }

    @Override
    public boolean execute() {
        return true;
    }

    @Override
    public void getDiscription(){
        System.out.println("Показывает содержимое коллекции");
    }
}
