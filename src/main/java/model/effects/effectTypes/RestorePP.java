package model.effects.effectTypes;

import model.effects.IEffect;
import model.entities.IPuckemon;

public class RestorePP implements IEffect {
    int amount;

    public RestorePP(int amount){
        this.amount = amount;
    }
    @Override
    public void execute(IPuckemon attackUser, IPuckemon opponent) {
            for(int i = 0; i < attackUser.getMoveSet().size();i++){
                attackUser.getMoveSet().get(i).incrementPP(amount);
            }
    }
}
