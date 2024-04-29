package ru.Timur.Command;

/**
 * Класс для инкапсуляции команды Help
 * @author timur
 */
public class HelpCommand implements Command {
    static final long serialVersionUID = 1L;
    private final Validation validation;

    public HelpCommand(Validation validation){
        this.validation = validation;
    }


    @Override
    public boolean execute() {
        return validation.help();
    }

    @Override
    public void getDiscription(){
        System.out.println("Выводит то, что делают команды");
    }
}
