package ru.Timur;

import java.util.*;

public class Arguments {
    private static PriorityQueue<String> collection = new PriorityQueue<>();

    public static int getSize(){
        return collection.size();
    }
    public static void addArg(String string){

        if(!string.isBlank() && !string.isEmpty()){
            collection.add(string);
        }
    }

    public static String getArg(){
        return collection.remove();
    }


    public static void clearArgs(){
        collection.clear();
    }

}
