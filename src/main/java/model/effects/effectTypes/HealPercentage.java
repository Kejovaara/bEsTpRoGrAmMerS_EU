package model.effects.effectTypes;

import model.effects.IEffect;
import model.puckemon.IPuckemon;

/**
 * A class that implements IEffect with the purpose of healing either the attackUser or opponent.
 * @author Rasmus Almryd
 */
public class HealPercentage implements IEffect {
    float percentage;
    Boolean healYourself;

    /**
     * Constructor of HealPercentage
     * @param percentage the percentage of the current health that should be added.
     */
    public HealPercentage(float percentage){
        this.percentage = percentage;
        healYourself = true;
    }

    /**
     * Constructor of HealPercentage
     * @param percentage the percentage of the current health that should be added.
     * @param healYourself true if effect should heal attackUser, false if it should heal opponent.
     */
    public HealPercentage(float percentage, Boolean healYourself){
        this.percentage = percentage;
        this.healYourself = healYourself;
    }

    /**
     * Heals an IPuckemon with the parentage of the current health that was defined in the constructor
     * @param attackUser the IPuckemon that used the effect
     * @param opponent the opposing IPuckemon
     */
    @Override
    public void execute(IPuckemon attackUser, IPuckemon opponent) {
        if(healYourself){
            int amount = Math.round(attackUser.getMaxHealth() * (percentage/100));
            attackUser.heal(amount);
        }else{
            int amount = Math.round(opponent.getMaxHealth() * (percentage/100));
            opponent.heal(amount);
        }
    }
}
