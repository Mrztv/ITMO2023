package ru.Timur;

public class Coordinates {
    private Integer x; //Поле не может быть null
    private Double y; //Значение поля должно быть больше -162, Поле не может быть null

    public Coordinates(Integer x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}