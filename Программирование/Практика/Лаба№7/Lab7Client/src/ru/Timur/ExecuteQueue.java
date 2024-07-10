//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ru.Timur;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import ru.Timur.Command.Command;

public class ExecuteQueue {
    private static Queue<Command> queue = new PriorityQueue(new Comparator<Command>() {
        public int compare(Command o1, Command o2) {
            return 0;
        }
    });

    public ExecuteQueue() {
    }

    public static void addInQueue(Command command) {
        queue.add(command);
    }

    public static Queue<Command> getQueue() {
        return new PriorityQueue(queue);
    }

    public static void clear() {
        queue.clear();
    }
}
