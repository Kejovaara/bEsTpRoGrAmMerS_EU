package model.effects.effectTypes;

import model.PTypes;
import model.effects.EffectHelper;
import model.effects.IEffect;
import model.entities.IPuckemon;
import view.animation.EffectAnimations;

public class HpSteal implements IEffect {

    private int power;
    private PTypes attackType;

    public HpSteal(int power, PTypes attackType){
        this.power = power;
        this.attackType = attackType;
    }

    public void execute(IPuckemon attackUser, IPuckemon opponent){
        int damage = EffectHelper.calculateDamage(attackUser,opponent,power,attackType);
        opponent.doDamage(damage);
        attackUser.heal((damage/2));
        EffectAnimations.getInstance().displayDamage(damage,opponent);
        EffectAnimations.getInstance().displayHealing(damage/2, attackUser);

    }

}
