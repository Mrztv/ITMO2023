package ru.Timur;

public class Chapter {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Long marinesCount; //Поле не может быть null, Значение поля должно быть больше 0, Максимальное значение поля: 1000

    public Chapter(String name, Long marinesCount) {
        this.name = name;
        this.marinesCount = marinesCount;
    }

    public String getName() {
        return name;
    }

    public Long getMarinesCount() {
        return marinesCount;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", marinesCount=" + marinesCount +
                '}';
    }
}