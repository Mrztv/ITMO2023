//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ru.Timur.Command;

import ru.Timur.User;

public class RemoveByIdCommand implements Command {
    static final long serialVersionUID = 1L;
    private User user = User.getUser();
    private final Validation validation;
    private Long id;

    public RemoveByIdCommand(Validation validation) {
        this.validation = validation;
    }

    public boolean execute() {
        this.user = User.getUser();
        this.id = this.validation.remove_by_id();
        return this.id != null;
    }

    public void getDiscription() {
        System.out.println("Удаляет элемент с заданным Id");
    }
}
