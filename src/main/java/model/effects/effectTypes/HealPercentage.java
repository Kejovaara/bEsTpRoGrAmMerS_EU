package model.effects.effectTypes;

import model.effects.IEffect;
import model.entities.IPuckemon;

public class HealPercentage implements IEffect {
    int percentage;
    Boolean healYourself;

    public HealPercentage(int percentage){
        this.percentage = percentage;
        healYourself = true;
    }

    public HealPercentage(int percentage, Boolean healYourself){
        this.percentage = percentage;
        this.healYourself = healYourself;
    }

    @Override
    public void execute(IPuckemon attackUser, IPuckemon opponent) {
        if(healYourself){
            attackUser.heal(attackUser.getHealth() * percentage);
        }else{
            opponent.heal(attackUser.getHealth() * percentage);
        }
    }
}
