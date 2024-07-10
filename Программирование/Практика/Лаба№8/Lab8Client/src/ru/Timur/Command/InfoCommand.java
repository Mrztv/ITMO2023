//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ru.Timur.Command;

import ru.Timur.User;

public class InfoCommand implements Command {
    static final long serialVersionUID = 1L;
    private User user = User.getUser();
    private final Validation validation;

    public InfoCommand(Validation validation) {
        this.validation = validation;
    }

    public boolean execute() {
        this.user = User.getUser();
        return true;
    }

    public void getDiscription() {
        System.out.println("Выводит информацию о коллекции");
    }
}
