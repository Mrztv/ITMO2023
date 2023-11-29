package ru.Timur.Move;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;

public class Hydro_Pump extends SpecialMove {
    public Hydro_Pump()
    {
        super(Type.WATER, 110, 80);
    }

    @Override
    protected void applyOppDamage(Pokemon def, double damage)
    {
        def.setMod(Stat.HP, (int) Math.round(damage));
        if(def.isAlive())
        {
            if(def.getStat(Stat.HP) < def.getHP() * 2)
            {
                System.out.println("I still can FIGHT");
            }
        }
    }
}
