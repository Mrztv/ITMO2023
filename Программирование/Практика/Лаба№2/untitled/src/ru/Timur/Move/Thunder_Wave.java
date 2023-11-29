package ru.Timur.Move;
import ru.ifmo.se.pokemon.*;

import java.lang.reflect.Parameter;

public class Thunder_Wave extends StatusMove {
    public Thunder_Wave() {
        super(Type.ELECTRIC, 0, 90);
    }

    @Override
    protected void applyOppEffects(Pokemon opp)
    {
        Effect eff = new Effect().condition(Status.PARALYZE);
        if(!opp.hasType(Type.ELECTRIC))
        {
            opp.setCondition(eff);
        }
    }
}
