package model.effects.effectTypes;

import model.effects.IEffect;
import model.entities.IPuckemon;

public class GiveXP implements IEffect {
    private int xp;

    public GiveXP(int xp){
        this.xp = xp;
    }

    public void execute(IPuckemon user, IPuckemon opp){
        //TODO
    }
}
