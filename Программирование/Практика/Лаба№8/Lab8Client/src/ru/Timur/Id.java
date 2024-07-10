//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ru.Timur;

public class Id {
    public static long id = 0L;

    public Id() {
    }

    public static long incAndGet() {
        return ++id;
    }

    public static void decId() {
        --id;
    }
}
