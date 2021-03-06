package model.effects.effectTypes;

import model.effects.IEffect;
import model.puckemon.IPuckemon;
import serviceControllers.observers.EffectHandler;

/**
 *A class that implements IEffect with the purpose of doing damage to the opponent.
 * @author Rasmus Almryd
 */
public class DoDamagePercentage implements IEffect {
    private final float percentage;

    /**
     * Constructor of DoDamagePercentage
     * @param percentage the percentage of health the opponent IPuckemon should loose
     */
    public DoDamagePercentage(float percentage){
        this.percentage = percentage;
    }

    /**
     * Removes the percentage of health that was defined in the constructor from the opponent Ipuckemon.
     * @param attackUser the Ipuckemon that used the effect
     * @param opponent the opposing IPuckemon
     */
    @Override
    public void execute(IPuckemon attackUser, IPuckemon opponent) {
        int damage = Math.round(opponent.getHealth()*(percentage/100));
        opponent.doDamage(damage);
        EffectHandler.getInstance().displayDamage(damage, opponent);
        //Animation-handler / Show damage
    }
}
