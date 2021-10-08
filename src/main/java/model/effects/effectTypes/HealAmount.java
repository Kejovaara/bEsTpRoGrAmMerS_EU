package model.effects.effectTypes;

import model.effects.IEffect;
import model.entities.IPuckemon;

public class HealAmount implements IEffect {
    int amount;
    Boolean healYourself;

    public HealAmount(int amount){
        this.amount = amount;
        healYourself = true;
    }

    public HealAmount(int amount, Boolean healYourself){
        this.amount = amount;
        this.healYourself = healYourself;
    }
    @Override
    public void execute(IPuckemon attackUser, IPuckemon opponent) {
        if(healYourself){
            attackUser.heal(amount);
        }else{
            opponent.heal(amount);
        }
    }
}
