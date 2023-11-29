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
}
