package model.effects.effectTypes;

import model.effects.IEffect;
import model.entities.puckemon.IPuckemon;

/**
 * Class that handles the effect to Restore PP of attacks in the game.
 * @author André Kejovaara
 */

public class RestorePP implements IEffect {
    int amount;

    /**
     * Constructor for RestorePP.
     * @param amount to be used to decide how much PP to be restored.
     */
    public RestorePP(int amount){
        this.amount = amount;
    }

    /**
     * Restores all PP to the attackers attacks.
     * @param attackUser the IPuckemon that used the effect
     * @param opponent the opposing IPuckemon
     */
    @Override
    public void execute(IPuckemon attackUser, IPuckemon opponent) {
            for(int i = 0; i < attackUser.getMoveSet().size();i++){
                attackUser.getMoveSet().get(i).incrementPP(amount);
            }
    }
}
