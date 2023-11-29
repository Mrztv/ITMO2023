package ru.Timur;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Battle;
import ru.Timur.Pokemon.*;

public class Main {
    public static void main(String[] args) {
        Battle b = new Battle();
        Pokemon p1 = new Diglett("Diglett", 1);
        Pokemon p2 = new Furret("Furret", 1);
        Pokemon p3 = new Dugtrio("Dugtrio", 1);
        Pokemon p4 = new Ambipom("Ambipom", 1);
        Pokemon p5 = new Gastrodon("Gastrodon", 1);
        Pokemon p6 = new Shellos("Shellos", 1);

        b.addAlly(p1);
        b.addAlly(p2);
        b.addAlly(p3);

        b.addFoe(p4);
        b.addFoe(p5);
        b.addFoe(p6);

        b.go();
    }
}