package model.attack;

import model.effects.IEffect;
import model.effects.IEffectContainer;
import model.entities.IPuckemon;

import java.util.List;

public class Attack implements IEffectContainer {
    private int priority;
    private List<IEffect> effects;

    public Attack(int priority, List<IEffect> effects){
        this.priority = priority;
        this.effects = effects;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public List<IEffect> getEffects() {
        return effects;
    }

    @Override
    public void execute(IPuckemon attackUser, IPuckemon opponent) {
        for (IEffect effect: effects) {
            effect.execute(attackUser, opponent);
        }
    }
}
