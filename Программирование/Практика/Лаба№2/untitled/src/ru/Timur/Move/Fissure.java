package ru.Timur.Move;

import ru.ifmo.se.pokemon.*;

public class Fissure extends PhysicalMove {
    public Fissure()
    {
        super(Type.GROUND, 0, 30);
    }

    @Override
    protected boolean checkAccuracy(Pokemon att, Pokemon def)
    {
        return 30 + att.getLevel() - def.getLevel() > Math.random();
    }

    @Override
    protected void applyOppEffects(Pokemon opp){
        Effect eff = new Effect().stat(Stat.HP, 0);
        opp.addEffect(eff);
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
 // 21121