package ru.Timur;

public class Id {
    public static long  id = 0;

    public static long incAndGet(){
        id += 1;
        return id;
    }

    public static void decId(){
        id -= 1;
    }

}
