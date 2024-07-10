//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ru.Timur.Command;

import ru.Timur.User;

public class FilterStartsWithNameCommand implements Command {
    static final long serialVersionUID = 1L;
    private final Validation validation;
    private User user = User.getUser();
    private String name;

    public FilterStartsWithNameCommand(Validation validation) {
        this.validation = validation;
    }

    public boolean execute() {
        this.user = User.getUser();
        this.name = this.validation.filterStartsWithName();
        if (this.name != null) {
            return true;
        } else {
            System.out.println("Неправильный аргумент");
            return false;
        }
    }

    public void getDiscription() {
        System.out.println("вывести элементы, значения поля name которых начинается с заданной подстроки");
    }
}
