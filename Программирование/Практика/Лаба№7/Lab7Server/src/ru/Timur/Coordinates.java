package ru.Timur;

import java.io.Serializable;

/**
 * Класс для координат
 * @author timur
 */
public class Coordinates implements Serializable {
    static final long serialVersionUID = 1L;
    /**
     * Поле для координаты x
     */
    private Integer x; //Поле не может быть null
    /**
     * Поле для координаты y
     */
    private Double y; //Значение поля должно быть больше -162, Поле не может быть null

    /**
     * Конструктор
     * @param x
     * @param y
     */
    public Coordinates(Integer x, Double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Получение координаты x
     * @return x
     */
    public Integer getX() {
        return x;
    }

    /**
     * Получение координаты y
     * @return y
     */
    public Double getY() {
        return y;
    }

    /**
     * @return строку для вывода
     */
    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}