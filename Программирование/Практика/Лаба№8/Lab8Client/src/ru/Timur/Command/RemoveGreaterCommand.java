//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ru.Timur.Command;

import java.io.IOException;
import java.io.Serializable;
import ru.Timur.SpaceMarine;
import ru.Timur.User;

public class RemoveGreaterCommand implements Command, Serializable {
    Validation validation;
    static final long serialVersionUID = 1L;
    private User user = User.getUser();
    private SpaceMarine spaceMarine;

    public RemoveGreaterCommand(Validation validation) {
        this.validation = validation;
    }

    public boolean execute() {
        this.user = User.getUser();

        try {
            this.spaceMarine = this.validation.removeGreater();
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
        System.out.println("удалить из коллекции все элементы, превышающий заданных");
    }
}
