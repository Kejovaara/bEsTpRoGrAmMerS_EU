package model.effects.effectTypes;

import model.PTypes;
import model.effects.EffectHelper;
import model.effects.IEffect;
import model.entities.IPuckemon;
import view.animation.EffectAnimationsHandler;

public class RecoilDamage implements IEffect {

    int power;
    PTypes attackType;
    double recoilPercentage;

    public RecoilDamage(int power, PTypes attackType, double recoilPercentage) {
        this.power = power;
        this.attackType = attackType;
        this.recoilPercentage = recoilPercentage;
    }

    @Override
    public void execute(IPuckemon attackUser, IPuckemon opponent) {
        int damage = EffectHelper.calculateDamage(attackUser,opponent,power,attackType);
        opponent.doDamage(damage);
        attackUser.doDamage((int)Math.ceil(damage*recoilPercentage));
        EffectAnimationsHandler.getInstance().displayDamage(damage, opponent);
        EffectAnimationsHandler.getInstance().displayDamage((int)Math.ceil(damage*recoilPercentage), attackUser);
    }
}
