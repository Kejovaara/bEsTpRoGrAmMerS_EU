package model.effects.effectTypes;

import model.PTypes;
import model.effects.EffectHelper;
import model.effects.IEffect;
import model.puckemon.IPuckemon;
import services.observers.EffectHandler;


/**
 *A class that implements IEffect with the purpose of doing damage to the opponent.
 * @author Rasmus Almryd
 */
public class DoDamage implements IEffect {

    private final int power;
    private final PTypes attackType;

    /**
     * Constructor of DoDamage
     * @param power to be used in the calculation of how much damage the opponent should take
     * @param attackType the type of the attack, different types deal different amount of damage
     */
    public DoDamage(int power, PTypes attackType){
        this.power = power;
        this.attackType = attackType;
    }

    /**
     * From the power(int) and PType defined in the constructor, and the IPuckemon's sent in to the method,
     * It calculates how much damage should be dealt to the opponents IPuckemon.
     * @param attackUser the Ipuckemon that used the effect
     * @param opponent the opposing IPuckemon
     */
    @Override
    public void execute(IPuckemon attackUser, IPuckemon opponent) {
        int damage = EffectHelper.calculateDamage(attackUser,opponent,power,attackType);
        opponent.doDamage(damage);
        EffectHandler.getInstance().displayDamage(damage, opponent);
    }
}
