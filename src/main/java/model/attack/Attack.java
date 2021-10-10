package model.attack;

import model.PTypes;
import model.effects.IEffect;
import model.effects.IEffectContainer;
import model.entities.IPuckemon;

import java.util.List;

public class Attack implements IEffectContainer {
    String name;
    private int priority;
    private List<IEffect> effects;
    private PTypes type;
    private int basePP;
    private int PP;

    public Attack(String name, int priority, List<IEffect> effects, int basePP, PTypes type){
        this.name = name;
        this.priority = priority;
        this.effects = effects;
        this.basePP = basePP;
        this.PP = this.basePP;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public List<IEffect> getEffects() {
        PP--;
        return effects;
    }

    @Override
    public void execute(IPuckemon attackUser, IPuckemon opponent) {
        PP--;
        for (IEffect effect: effects) {
            effect.execute(attackUser, opponent);
        }
    }

    public int getBasePP() {
        return basePP;
    }

    public int getPP() {
        return PP;
    }

    public void addPP(int amount){
        PP += amount;
    }

    public PTypes getType() {
        return type;
    }
}
