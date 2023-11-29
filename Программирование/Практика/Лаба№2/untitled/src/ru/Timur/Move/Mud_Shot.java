package ru.Timur.Move;

import ru.ifmo.se.pokemon.*;

public class Mud_Shot extends SpecialMove {
    public Mud_Shot()
    {
        super(Type.GROUND, 55, 95);
    }

    @Override
    protected void applyOppEffects(Pokemon opp)
    {
        opp.setMod(Stat.SPEED, -1);
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
