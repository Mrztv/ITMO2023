//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ru.Timur.Command;

import ru.Timur.User;

public class CountGreaterThanHealthCommand implements Command {
    private final Validation validation;
    private User user = User.getUser();
    private static final long serialVersionUID = 1L;
    private Float health;

    public CountGreaterThanHealthCommand(Validation validation) {
        this.validation = validation;
    }

    public boolean execute() {
        this.user = User.getUser();
        this.health = this.validation.countGreaterThanHealth();
        return this.health != null;
    }

    public void getDiscription() {
        System.out.println("вывести количество элементов, значение поля health которых больше заданного");
    }
}
