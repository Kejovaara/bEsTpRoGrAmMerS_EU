package model.effects.effectTypes;

import model.PTypes;
import model.effects.EffectHelper;
import model.effects.IEffect;
import model.entities.IPuckemon;

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
        opponent.setHealth(opponent.getHealth() - damage);
        attackUser.setHealth(attackUser.getHealth() - (int)Math.round(damage*recoilPercentage));
    }
}
