//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ru.Timur;

import java.io.Serializable;

public class Coordinates implements Serializable {
    static final long serialVersionUID = 1L;
    private Integer x;
    private Double y;

    public Coordinates(Integer x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return this.x;
    }

    public Double getY() {
        return this.y;
    }

    public String toString() {
        return "{x=" + this.x + ", y=" + this.y + "}";
    }
}
