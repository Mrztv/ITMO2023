package ru.Timur.Pokemon;

import ru.Timur.Move.*;
public class Dugtrio extends Diglett{
    public Dugtrio(String _name, int _lvl)
    {
        super(_name, _lvl);
        this.setStats(35, 100, 50, 50, 70, 120);
        this.addMove(new Fissure());
    }
}
