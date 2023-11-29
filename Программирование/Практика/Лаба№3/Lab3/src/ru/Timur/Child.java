package ru.Timur;

import java.util.Objects;

public class Child extends Human implements Sneakable, Hideable {
    private Interior location; // поменять тип данных

    public Child(String name) {
        super(name);
    }

    @Override
    public void sneak(String location) {
        System.out.println(getName() + " крадется в " + location);
    }

    @Override
    public void hide(Interior loc) {
        location = loc;
        System.out.println(getName() + " прячется, используя " + location.getName());
    }

    public void think(String think) {
        System.out.println(getName() + " думает: \"" + think + "\"");
    }

    public Interior getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Child child = (Child) o;
        return location == child.location;
    }

    @Override
    public int hashCode() {
        return Objects.hash(location);
    }

    @Override
    public String toString() {
        return "Child{" +
                "name:" + this.getName() +
                "location" + location.toString() +
                "}";
    }
}
