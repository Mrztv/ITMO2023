package ru.Timur.Pokemon;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;
import ru.Timur.Move.*;

public class Diglett extends Pokemon {
    public Diglett(String _name, int _lvl){
        super(_name, _lvl);
        this.setStats(10, 55, 25, 35, 45, 95);
        this.setType(Type.GROUND);
        this.setMove(new Focus_Energy(), new Thunder_Wave(), new Thunder_Shock());
    }
}
