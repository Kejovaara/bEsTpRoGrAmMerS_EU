package model.effects.effectTypes;

import model.effects.IEffect;
import model.entities.IPuckemon;

public class RestorePP implements IEffect {
    int amount;
    boolean targetYourself;

    public RestorePP(int amount){
        this.amount = amount;
        targetYourself = true;
    }
    @Override
    public void execute(IPuckemon attackUser, IPuckemon opponent) {
        if(targetYourself){
           //TODO

        }else{
            //TODO
        }
    }
}
