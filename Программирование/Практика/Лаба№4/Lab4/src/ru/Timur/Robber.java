package ru.Timur;

import java.util.Objects;

public class Robber extends Human implements Sneakable, LookBack, Dropable {
    String location = null;

    public Robber(String name) {
        super(name);
    }

    @Override
    public void sneak(String location) {
        this.location = location;
        System.out.println(getName() + " крадется в " + location);
    }

    public String getLocation(){
        return location;
    }

    public void whistle() {
        System.out.println(getName() + " свистит");
    }

    public void stopWhistle(){
        System.out.println(getName() + " перестал свистеть");
    }
    public void search(Interior where) {
        System.out.print(this.getName() + " нашел: ");
        for (String a : where.getContains()) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void makeSound(String type) {
        class Sound{
            String typeOfSound;
            public Sound(String sound){
                typeOfSound = sound;
            }

            public String getTypeOfSound() {
                return typeOfSound;
            }
        }
        Sound sound = new Sound(type);
        System.out.println(this.getName() + " " + sound.getTypeOfSound());
    }

    @Override
    public void lookBack(){
        System.out.println(this.getName() + " обернулся");
    }

    public void drop(String things){
        System.out.println(this.getName() + " уронил " + things);
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
