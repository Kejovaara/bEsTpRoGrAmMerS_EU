package model.effects.effectTypes;

import model.PTypes;
import model.effects.EffectHelper;
import model.effects.IEffect;
import model.entities.IPuckemon;
import view.animation.EffectAnimationsHandler;

public class DoDamagePercentage implements IEffect {
    private float percentage;
    PTypes attackType;

    public DoDamagePercentage(float percentage, PTypes attackType){
        this.percentage = percentage;
        this.attackType = attackType;
    }

    @Override
    public void execute(IPuckemon attackUser, IPuckemon opponent) {
        int damage = Math.round(opponent.getHealth()*percentage);
        opponent.doDamage(damage);
        EffectAnimationsHandler.getInstance().displayDamage(damage, opponent);
        //Animation-handler / Show damage
    }
}
