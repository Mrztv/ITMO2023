package ru.Timur;

import ru.Timur.Command.Command;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class ExecuteQueue {
    private static Queue<Command> queue = new PriorityQueue<>(new Comparator<Command>() {
        @Override
        public int compare(Command o1, Command o2) {
            return 0;
        }
    });

    public static void addInQueue(Command command){
        queue.add(command);
    }

    public static Queue<Command> getQueue() {
        return new PriorityQueue<>(queue);
    }

    public static void clear(){
        queue.clear();
    }

}
