package ru.Timur.Move;

import ru.ifmo.se.pokemon.*;

public class Flail extends PhysicalMove {
    final double maxHP;
    public Flail(Pokemon self)
    {
        super(Type.NORMAL, 20, 100);
        maxHP = self.getHP();
    }

    @Override
    protected void applySelfEffects(Pokemon self) {
        double N = 48*self.getHP()/maxHP;
        if(N >= 0 && N < 2)
        {
            power = 200;
        } else if (N >= 2 && N < 4) {
            power = 150;
        } else if (N >= 4 && N < 9) {
            power = 100;
        } else if (N >= 9 && N < 16) {
            power = 80;
        } else if (N >= 16 && N < 32) {
            power = 40;
        } else if (N >= 32 && N < 48) {
            power = 20;
        }

    }

}
