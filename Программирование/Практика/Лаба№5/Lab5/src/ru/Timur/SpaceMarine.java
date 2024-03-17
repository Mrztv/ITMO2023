package ru.Timur;

import ru.Timur.Command.Command;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.time.ZonedDateTime;

public class SpaceMarine implements Comparable<SpaceMarine>{

    private final Long id = Id.incAndGet(); //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private final java.time.ZonedDateTime creationDate = java.time.ZonedDateTime.now(); //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Float health; //Поле не может быть null, Значение поля должно быть больше 0
    private boolean loyal;
    private String achievements; //Поле может быть null
    private AstartesCategory category; //Поле может быть null
    private Chapter chapter; //Поле не может быть null
    public SpaceMarine(){}

    public SpaceMarine(String name, Coordinates coordinates, Float health, boolean loyal, String achievements, AstartesCategory category, Chapter chapter) {
        this.name = name;
        this.coordinates = coordinates;
        this.health = health;
        this.loyal = loyal;
        this.achievements = achievements;
        this.category = category;
        this.chapter = chapter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setHealth(Float health) {
        this.health = health;
    }

    public void setLoyal(boolean loyal) {
        this.loyal = loyal;
    }

    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }

    public void setCategory(AstartesCategory category) {
        this.category = category;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Float getHealth() {
        return health;
    }

    public boolean isLoyal() {
        return loyal;
    }

    public String getAchievements() {
        return achievements;
    }

    public AstartesCategory getCategory() {
        return category;
    }

    public Chapter getChapter() {
        return chapter;
    }


    @Override
    public int compareTo(SpaceMarine spaceMarine) {
        return this.id.compareTo(spaceMarine.id);
    }

    @Override
    public String toString() {
        return "SpaceMarine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates.toString() +
                ", creationDate=" + creationDate +
                ", health=" + health +
                ", loyal=" + loyal +
                ", achievements='" + achievements + '\'' +
                ", category=" + category +
                ", chapter=" + chapter.toString() +
                '}';
    }
}
