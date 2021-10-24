package model.effects.effectTypes;

import model.PTypes;
import model.effects.EffectHelper;
import model.effects.IEffect;
import model.puckemon.IPuckemon;
import services.observers.EffectHandler;

/**
 * A class that implements IEffect with the purpose of doing damage to an
 * opponent and giving a fraction of that damage to the attacker in tha form
 * of health points.
 * @author Rasmus Almryd
 */
public class HpSteal implements IEffect {

    private final int power;
    private final PTypes attackType;
    float fraction;

    /**
     * Constructor of HpSteal
     * @param power to be used in the calculation of how much damage the opponent should take.
     * @param attackType the type of the attack, different types deal different amount of damage.
     * @param fraction the fraction of the damage dealt that should be given as healt points to the attacker.
     */
    public HpSteal(int power, PTypes attackType, float fraction){
        this.power = power;
        this.attackType = attackType;
        this.fraction = fraction;
    }

    /**
     * Does damage to an opponent and giving a fraction of that damage
     * to the attacker in the form of health points.
     * @param attackUser the IPuckemon that used the effect
     * @param opponent the opposing IPuckemon
     */
    public void execute(IPuckemon attackUser, IPuckemon opponent){
        int damage = EffectHelper.calculateDamage(attackUser,opponent,power,attackType);
        opponent.doDamage(damage);
        attackUser.heal((int)Math.ceil((float)damage*fraction));
        EffectHandler.getInstance().displayDamage(damage,opponent);
        EffectHandler.getInstance().displayHealing((int)Math.ceil(damage*fraction), attackUser);

    }

}
