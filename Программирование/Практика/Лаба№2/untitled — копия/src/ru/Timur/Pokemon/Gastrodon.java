package ru.Timur.Pokemon;

import ru.ifmo.se.pokemon.Type;
import ru.Timur.Move.*;

public class Gastrodon extends Shellos{
    double _HP = 111;
    double _ATT = 83;
    double _DEF = 68;
    double _SpATT = 92;
    double _SpDEF = 82;
    double _SPEED = 39;
    public Gastrodon(String _name, int _lvl){
        super(_name, _lvl);
        this.setStats(_HP, _ATT, _DEF, _SpATT, _SpDEF, _SPEED);
        this.addType(Type.GROUND);
        this.addMove(new Mud_Shot());
    }
}
