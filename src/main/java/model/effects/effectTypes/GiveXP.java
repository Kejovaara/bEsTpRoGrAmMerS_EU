package model.effects.effectTypes;

import model.effects.IEffect;
import model.entities.IPuckemon;

/**
 * Class to handle the effect from items that gives XP.
 * @author André Kejovaara
 */

public class GiveXP implements IEffect {
    private int xp;

    /**
     * Constructor for GiveXP
     * @param xp is used for how much XP to give.
     */
    public GiveXP(int xp){
        this.xp = xp;
    }

    public void execute(IPuckemon user, IPuckemon opp){
        //TODO
    }
}
