//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ru.Timur.Command;

import java.io.Serializable;
import ru.Timur.User;

public class AverageOfHealthCommand implements Command, Serializable {
    private final Validation validation;
    private User user = User.getUser();
    private static final long serialVersionUID = 1L;

    public AverageOfHealthCommand(Validation validation) {
        this.validation = validation;
    }

    public boolean execute() {
        this.user = User.getUser();
        return true;
    }

    public void getDiscription() {
        System.out.println("вывести среднее значение поля health для всех элементов коллекции");
    }
}
