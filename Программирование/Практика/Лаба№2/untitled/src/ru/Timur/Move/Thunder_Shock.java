package ru.Timur.Move;

import ru.ifmo.se.pokemon.*;

public class Thunder_Shock extends SpecialMove {
    public Thunder_Shock()
    {
        super(Type.ELECTRIC, 40, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon opp)
    {
        Effect eff = new Effect().chance(0.1).condition(Status.PARALYZE);
        if(!opp.hasType(Type.ELECTRIC))
        {
            opp.setCondition(eff);
        }
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
