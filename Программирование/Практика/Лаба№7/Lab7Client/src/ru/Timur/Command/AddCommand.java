//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ru.Timur.Command;

import java.io.IOException;
import java.io.Serializable;
import java.util.InputMismatchException;
import ru.Timur.SpaceMarine;
import ru.Timur.User;
import ru.Timur.Exceptions.NonValidFileElementException;

public class AddCommand implements Command, Serializable {
    static final long serialVersionUID = 1L;
    private User user = User.getUser();
    private Validation validation;
    private SpaceMarine spaceMarine;

    public AddCommand(Validation validation) {
        this.validation = validation;
    }

    public String toString() {
        return "AddCommand{validation=" + this.validation + ", spaceMarine=" + this.spaceMarine + "}";
    }

    public boolean execute() {
        this.user = User.getUser();

        try {
            this.spaceMarine = this.validation.add();
            if (this.spaceMarine == null) {
                throw new IOException();
            } else {
                return true;
            }
        } catch (NonValidFileElementException | InputMismatchException | IOException var2) {
            System.out.println(var2.toString());
            throw new RuntimeException(var2.getMessage());
        }
    }

    public void getDiscription() {
        System.out.println("Добавляет элемент в коллекцию");
    }
}
