//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ru.Timur.Command;

import java.util.PriorityQueue;

public class Arguments {
    private static PriorityQueue<String> collection = new PriorityQueue();

    public Arguments() {
    }

    public static int getSize() {
        return collection.size();
    }

    public static void addArg(String string) {
        if (!string.isBlank() && !string.isEmpty()) {
            collection.add(string);
        }

    }

    public static String getArg() {
        return (String)collection.remove();
    }

    public static void clearArgs() {
        collection.clear();
    }
}
