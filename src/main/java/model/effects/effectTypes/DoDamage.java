package model.effects.effectTypes;

import model.PTypes;
import model.effects.EffectHelper;
import model.effects.IEffect;
import model.entities.IPuckemon;
import view.animation.EffectAnimationsHandler;


public class DoDamage implements IEffect {

    int power;
    PTypes attackType;

    public DoDamage(int power, PTypes attackType){
        this.power = power;
        this.attackType = attackType;
    }

    @Override
    public void execute(IPuckemon attackUser, IPuckemon opponent) {
        int damage = EffectHelper.calculateDamage(attackUser,opponent,power,attackType);
        opponent.doDamage(damage);
        EffectAnimationsHandler.getInstance().displayDamage(damage, opponent);
        //Animation-handler / Show damage
    }
}
