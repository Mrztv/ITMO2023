package ru.Timur.Command;

import ru.Timur.ClientData;
import ru.Timur.Pair;

import java.net.InetSocketAddress;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CommandExecuteThread  implements Callable<ClientData>{
    private Command command;

    public CommandExecuteThread(Command command) {
        this.command = command;
    }

    @Override
    public ClientData call() throws Exception {
        if (command != null) {
            command.setStorage(Storage.getStorage());
            return command.execute();
        }
        else return new ClientData("Неверное значение");
    }
}
