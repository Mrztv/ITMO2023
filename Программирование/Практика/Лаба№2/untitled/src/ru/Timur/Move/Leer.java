package ru.Timur.Move;

import ru.ifmo.se.pokemon.*;

public class Leer extends StatusMove {
    public Leer()
    {
        super(Type.NORMAL, 0, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon p)
    {
        Effect eff = new Effect().stat(Stat.DEFENSE, -1).turns(-1);
        p.addEffect(eff);
    }

}
