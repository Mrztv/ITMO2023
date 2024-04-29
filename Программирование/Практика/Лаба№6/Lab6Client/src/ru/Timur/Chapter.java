package ru.Timur;

import java.io.Serializable;

/**
 * Класс для части {@link SpaceMarine}
 * @author timur
 */
public class Chapter implements Serializable {
    static final long serialVersionUID = 1L;
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Long marinesCount; //Поле не может быть null, Значение поля должно быть больше 0, Максимальное значение поля: 1000

    /**
     * Конструктор
     * @param name
     * @param marinesCount
     */
    public Chapter(String name, Long marinesCount) {
        this.name = name;
        this.marinesCount = marinesCount;
    }

    /**
     * Получение имени части
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Получение количества морпехов в части
     * @return name
     */
    public Long getMarinesCount() {
        return marinesCount;
    }

    /**
     * @return строка для вывода
     */
    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", marinesCount=" + marinesCount +
                '}';
    }
}