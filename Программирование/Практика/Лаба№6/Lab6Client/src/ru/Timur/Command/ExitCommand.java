package ru.Timur.Command;

/**
 * Класс для инкапсуляции команды Exit
 * @author timur
 */
public class ExitCommand implements Command {
    static final long serialVersionUID = 1L;
    private final Validation validation;

    public ExitCommand(Validation validation){
        this.validation = validation;
    }
    @Override
    public boolean execute() {
        validation.exit();
        return true;
    }

    @Override
    public void getDiscription(){
        System.out.println("Останавливает программу");
    }
}
