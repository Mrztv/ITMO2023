package ru.Timur;

import java.util.Objects;

public class Robber extends Human implements Sneakable {

    public Robber(String name) {
        super(name);
    }

    @Override
    public void sneak(String location) {
        System.out.println(getName() + " крадется в " + location);
    }

    public void whistle() {
        System.out.println(getName() + " свистит");
    }

    public void search(Interior where) {
        System.out.print(this.getName() + " нашел: ");
        for (String a : where.getContains()) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Robber that = (Robber) obj;
        return Objects.equals(that.getName(), getName());
    }

    @Override
    public String toString() {
        return "Robber{" +
                "name: " + this.getName() +
                "}";
    }
}
