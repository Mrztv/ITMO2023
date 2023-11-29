package ru.Timur.Pokemon;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;
import ru.Timur.Move.*;

public class Shellos extends Pokemon {
    double _HP = 76;
    double _ATT = 48;
    double _DEF = 48;
    double _SpATT = 57;
    double _SpDEF = 62;
    double _SPEED = 34;
    public Shellos(String _name, int _lvl)
    {
        super(_name, _lvl);
        this.setType(Type.WATER);
        this.setStats(_HP, _ATT, _DEF, _SpATT, _SpDEF, _SPEED);
        this.setMove(new Thunder_Shock(), new Hydro_Pump());
    }
}
