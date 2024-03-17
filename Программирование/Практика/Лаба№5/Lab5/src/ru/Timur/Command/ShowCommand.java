package ru.Timur.Command;

public class ShowCommand implements Command {
    private final Storage storage;

    public ShowCommand(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void execute() {
        storage.show();
    }

    @Override
    public void getDiscription(){
        System.out.println("Показывает содержимое коллекции");
    }
}
