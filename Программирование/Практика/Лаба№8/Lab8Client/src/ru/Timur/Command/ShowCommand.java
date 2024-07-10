//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ru.Timur.Command;

import ru.Timur.User;

public class ShowCommand implements Command {
    private final transient Validation validation;
    private User user = User.getUser();
    private static final long serialVersionUID = 1L;

    public ShowCommand(Validation validation) {
        this.validation = validation;
    }

    public boolean execute() {
        this.user = User.getUser();
        return true;
    }

    public void getDiscription() {
        System.out.println("Показывает содержимое коллекции");
    }
}
