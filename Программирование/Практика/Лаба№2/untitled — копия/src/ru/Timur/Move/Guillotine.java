package ru.Timur.Move;

import ru.ifmo.se.pokemon.*;

public class Guillotine extends PhysicalMove {
    public Guillotine(){
        super(Type.NORMAL, 0, 30);
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
}
