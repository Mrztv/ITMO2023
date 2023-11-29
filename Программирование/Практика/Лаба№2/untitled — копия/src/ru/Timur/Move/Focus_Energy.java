package ru.Timur.Move;

import ru.ifmo.se.pokemon.*;

public class Focus_Energy extends StatusMove {
    public Focus_Energy()
    {
        super(Type.NORMAL, 0, 0);
    }

    @Override
    protected void applySelfEffects(Pokemon self){
        Effect eff = new Effect().stat(Stat.SPEED, +10);
        self.addEffect(eff);
    }

    @Override
    protected boolean checkAccuracy(Pokemon att, Pokemon def) {
        return true;
    }
}
