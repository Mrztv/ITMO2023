package ru.Timur;

import java.util.Arrays;

public enum Interior {
    SOFA("диван"),
    SOME("что-то"),
    FIREPLACE("камин"),
    BUFFET("буфет"),
    SECRETAIRE("секретар");

    private final String name;
    String[] contains;

    Interior(String rus_name) {
        name = rus_name;
    }

    public String getName() {
        return name;
    }

    public void setContains(String[] contains) {
        this.contains = contains;
    }

    public String[] getContains() {
        return contains;
    }

    @Override
    public String toString() {
        return "Interior{" +
                "name='" + name + '\'' +
                ", contains=" + Arrays.toString(contains) +
                '}';
    }
}
