package ru.Timur.Move;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Status;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

import java.security.cert.PolicyNode;
import java.util.ArrayList;

public class SleepTalk extends StatusMove {
    public SleepTalk()
    {
        super(Type.NORMAL, 0, 100);
    }

    @Override
    protected void applySelfEffects(Pokemon self)
    {
        if(self.getCondition() == Status.SLEEP)
        {
            self.prepareMove();
        }
    }

    @Override
    protected boolean checkAccuracy(Pokemon att, Pokemon def){
        return true;
    }


}
