package ru.Timur;

import java.io.Serializable;
import java.util.Set;

public class ClientData implements Serializable {
    static final long serialVersionUID = 1L;
    private int variant;
    private Set<SpaceMarine> collection;
    private String out;
    private Integer number;


    public void write() {
        if(variant == 1){
            collection.forEach(System.out::println);
        }
        else if(variant == 2){
            System.out.println(out);
        }
        else if (variant == 3){
            System.out.println(number);
        }
        else {
            System.out.println("ERR");
        }
    }
}
