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

    /**
     * Constructor of Attack Class
     * @param name used to set the name of the Attack.
     * @param priority used to determine which action is used first in combat.
     * @param effects used to roll out effects as a side effect of an attack.
     * @param basePP used to limit the amount of times an attack can be used.
     * @param type used to determine what type an attack is.
     */
    public Attack(String name, int priority, List<IEffect> effects, int basePP, PTypes type){
        this.name = name;
        this.priority = priority;
        this.effects = effects;
        this.basePP = basePP;
        this.PP = this.basePP;
        this.type = type;
    }

    /**
     * @return the name of an attack.
     */
    public String getName() {
        return name;
    }

    /**
     * @return the priority of an attack.
     */
    @Override
    public int getPriority() {
        return priority;
    }

    /**
     * @return a list of effects.
     */
    @Override
    public List<IEffect> getEffects() {
        PP--;
        return effects;
    }

    /**
     *
     * @param attackUser the Ipuckemon that used the effect
     * @param opponent the opposing IPuckemon
     */
    @Override
    public void execute(IPuckemon attackUser, IPuckemon opponent) {
        PP--;
        for (IEffect effect: effects) {
            effect.execute(attackUser, opponent);
        }
    }

    /**
     * @return the basePP of an attack.
     */
    public int getBasePP() {
        return basePP;
    }

    /**
     * @return current PP of an attack.
     */
    public int getPP() {
        return PP;
    }

    //PRIVATE TO PREVENT UNAUTHORIZED USE OF setPP()
    private void setPP(int num){
        PP = num;
    }

    /**
     * Is used to increment the PP of an attack with a given num
     * @param num is the amount to be increased
     */
    public void incrementPP(int num){
        PP += num;
        if(PP > basePP){
            setPP(basePP);
        }
    }

    /**
     * @return a type of attack.
     */
    public PTypes getType() {
        return type;
    }
}
