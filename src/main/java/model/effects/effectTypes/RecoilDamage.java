package model.effects.effectTypes;

import model.PTypes;
import model.effects.EffectHelper;
import model.effects.IEffect;
import model.entities.puckemon.IPuckemon;
import services.observers.EffectHandler;

/**
 * A class that implements IEffect with the purpose of doing damage to an
 * opponent and dealing a fraction of that damage to the attacker.
 * @author Rasmus Almryd
 */
public class RecoilDamage implements IEffect {

    int power;
    PTypes attackType;
    double fraction;

    /**
     * Constructor of RecoilDamage
     * @param power to be used in the calculation of how much damage the opponent should take.
     * @param attackType the type of the attack, different types deal different amount of damage.
     * @param fraction the fraction of the amount of damage dealt to the opponent that should be dealt to the attacker.
     */
    public RecoilDamage(int power, PTypes attackType, double fraction) {
        this.power = power;
        this.attackType = attackType;
        this.fraction = fraction;
    }

    /**
     * Does damage to an opponent and dealing a fraction of that damage
     * to the attacker.
     * @param attackUser the IPuckemon that used the effect
     * @param opponent the opposing IPuckemon
     */
    @Override
    public void execute(IPuckemon attackUser, IPuckemon opponent) {
        int damage = EffectHelper.calculateDamage(attackUser,opponent,power,attackType);
        opponent.doDamage(damage);
        attackUser.doDamage((int)Math.ceil(damage*fraction));
        EffectHandler.getInstance().displayDamage(damage, opponent);
        EffectHandler.getInstance().displayDamage((int)Math.ceil(damage*fraction), attackUser);
    }
}
