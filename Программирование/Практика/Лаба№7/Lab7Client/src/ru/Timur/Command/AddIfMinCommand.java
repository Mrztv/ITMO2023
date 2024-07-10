//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ru.Timur.Command;

import java.io.IOException;
import java.io.Serializable;
import ru.Timur.SpaceMarine;
import ru.Timur.User;

public class AddIfMinCommand implements Command, Serializable {
    private static final long serialVersionUID = 1L;
    private final Validation validation;
    private SpaceMarine spaceMarine;
    private User user = User.getUser();

    public AddIfMinCommand(Validation validation) {
        this.validation = validation;
    }

    public boolean execute() {
        this.user = User.getUser();

        try {
            this.spaceMarine = this.validation.addIfMin();
            if (this.spaceMarine == null) {
                throw new IOException();
            } else {
                return true;
            }
        } catch (IOException var2) {
            System.out.println(var2.toString());
            return false;
        }
    }

    public void getDiscription() {
        System.out.println("Добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
    }
}
