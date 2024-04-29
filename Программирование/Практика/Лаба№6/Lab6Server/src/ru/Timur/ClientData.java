package ru.Timur;

import java.io.Serializable;
import java.util.Set;

public class ClientData implements Serializable {
    static final long serialVersionUID = 1L;
    private int variant;
    private Set<SpaceMarine> collection = null;
    private String out = null;

    private Number number = null;
    public ClientData(Set<SpaceMarine> collection){
        this.collection = collection;
        variant = 1;
    }

    public ClientData(String out){
        this.out = out;
        variant = 2;
    }

    public ClientData(Number number){
        this.number = number;
        variant = 3;
    }

}
