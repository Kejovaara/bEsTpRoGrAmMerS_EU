package model.effects.effectTypes;

import model.effects.IEffect;
import model.entities.puckemon.IPuckemon;
import services.observers.EffectHandler;

/**
 * A class that implements IEffect with the purpose of healing either the attackUser or opponent.
 * @author Rasmus Almryd
 */
public class HealAmount implements IEffect {
    int amount;
    Boolean healYourself;

    /**
     * Constructor of HealAmount
     * @param amount the amount of Health points that should be restored to the attackUser.
     */
    public HealAmount(int amount){
        this.amount = amount;
        healYourself = true;
    }

    /**
     * Constructor of HealAmount
     * @param amount the amount of Health points that should be restored
     * @param healYourself true if effect should heal attackUser, false if it should heal opponent
     */
    public HealAmount(int amount, Boolean healYourself){
        this.amount = amount;
        this.healYourself = healYourself;
    }

    /**
     * Heals an IPuckemon with the amount of health points that was defined in the constructor
     * @param attackUser the IPuckemon that used the effect
     * @param opponent the opposing IPuckemon
     */
    @Override
    public void execute(IPuckemon attackUser, IPuckemon opponent) {
        if(healYourself){
            attackUser.heal(amount);
        }else{
            opponent.heal(amount);
        }
        EffectHandler.getInstance().displayHealing(amount,attackUser);
    }
}
