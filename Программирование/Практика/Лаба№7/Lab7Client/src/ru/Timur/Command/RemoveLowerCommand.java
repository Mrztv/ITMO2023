//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ru.Timur.Command;

import ru.Timur.SpaceMarine;
import ru.Timur.User;

public class RemoveLowerCommand implements Command {
    static final long serialVersionUID = 1L;
    private final Validation validation;
    private User user = User.getUser();
    private SpaceMarine spaceMarine;

    public RemoveLowerCommand(Validation validation) {
        this.validation = validation;
    }

    public boolean execute() {
        this.user = User.getUser();
        this.spaceMarine = this.validation.removeLower();
        return this.spaceMarine != null;
    }

    public void getDiscription() {
        System.out.println("удалить из коллекции все элементы, меньшие, чем заданный");
    }
}
