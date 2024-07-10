//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ru.Timur.Command;

import ru.Timur.User;

public class ExitCommand implements Command {
    static final long serialVersionUID = 1L;
    private final Validation validation;
    private User user = User.getUser();

    public ExitCommand(Validation validation) {
        this.validation = validation;
    }

    public boolean execute() {
        this.user = User.getUser();
        this.validation.exit();
        return true;
    }

    public void getDiscription() {
        System.out.println("Останавливает программу");
    }
}
