//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ru.Timur;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class SpaceMarine implements Comparable<SpaceMarine>, Serializable {
    static final long serialVersionUID = 1L;
    private Long id = Id.incAndGet();
    private String name;
    private Coordinates coordinates;
    private final ZonedDateTime creationDate = ZonedDateTime.now();
    private Float health;
    private boolean loyal;
    private String achievements;
    private AstartesCategory category;
    private Chapter chapter;
    private String user;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public Float getHealth() {
        return this.health;
    }

    public boolean isLoyal() {
        return this.loyal;
    }

    public String getAchievements() {
        return this.achievements;
    }

    public AstartesCategory getCategory() {
        return this.category;
    }

    public Chapter getChapter() {
        return this.chapter;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public String getUser() {
        return user;
    }

    public int compareTo(SpaceMarine spaceMarine) {
        return this.id.compareTo(spaceMarine.id);
    }

    public String toString() {
        return "SpaceMarine{id=" + this.id + ", user='" + this.user + ", name='" + this.name + "', coordinates=" + this.coordinates.toString() + ", creationDate=" + this.creationDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss z")) + ", health=" + this.health + ", loyal=" + this.loyal + ", achievements='" + this.achievements + "', category=" + this.category + ", chapter=" + this.chapter.toString() + "}";
    }
}
