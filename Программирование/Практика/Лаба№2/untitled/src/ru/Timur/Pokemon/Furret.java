package ru.Timur.Pokemon;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;
import ru.Timur.Move.*;

public class Furret extends Pokemon {
    double maxHP;
    public Furret(String _name, int _lvl)
    {
        super(_name, _lvl);
        this.setType(Type.NORMAL);
        this.setStats(85, 76, 64, 45, 55, 90);
        maxHP = 85;
        setMove(new Flail(this), new Leer(), new SleepTalk());

    }



    public double getMaxHP()
    {
        return maxHP;
    }
    public void setMaxHP(double _maxHP)
    {
        maxHP = _maxHP;
    }

}
