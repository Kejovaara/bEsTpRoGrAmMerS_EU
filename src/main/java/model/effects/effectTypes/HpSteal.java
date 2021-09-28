package model.effects.effectTypes;

import model.PTypes;
import model.effects.EffectHelper;
import model.effects.IEffect;
import model.entities.IPuckemon;

public class HpSteal implements IEffect {

    private int power;
    private PTypes attackType;

    public HpSteal(int power, PTypes attackType){
        this.power = power;
        this.attackType = attackType;
    }

    public void execute(IPuckemon attackUser, IPuckemon opponent){
        int damage = EffectHelper.calculateDamage(attackUser,opponent,power,attackType);
        opponent.setHealth(opponent.getHealth() - damage);
        attackUser.setHealth(attackUser.getHealth() + (damage/2));
    }

}
