package ru.Timur.Pokemon;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;
import ru.Timur.Move.*;

public class Ambipom extends Pokemon {
    public Ambipom(String _name, int _lvl)
    {
        super(_name, _lvl);
        this.setType(Type.NORMAL);
        this.setStats(75, 100, 66, 60, 66, 115);
        this.setMove(new Thunder_Shock(), new Hydro_Pump(), new Mud_Shot(), new Guillotine());
    }
}
