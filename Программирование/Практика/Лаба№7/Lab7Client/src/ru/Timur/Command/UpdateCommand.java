//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ru.Timur.Command;

import java.io.IOException;
import java.util.InputMismatchException;
import ru.Timur.SpaceMarine;
import ru.Timur.User;
import ru.Timur.Exceptions.NonValidFileElementException;

public class UpdateCommand implements Command {
    static final long serialVersionUID = 1L;
    private final Validation validation;
    private User user = User.getUser();
    private SpaceMarine spaceMarine;

    public UpdateCommand(Validation validation) {
        this.validation = validation;
    }

    public boolean execute() {
        this.user = User.getUser();

        try {
            this.spaceMarine = this.validation.update();
            return this.spaceMarine != null;
        } catch (NonValidFileElementException | InputMismatchException | IOException var2) {
            System.out.println(var2.toString());
            throw new RuntimeException(var2.getMessage());
        }
    }

    public void getDiscription() {
        System.out.println("Обновляет элемент по заданному ID");
    }
}
