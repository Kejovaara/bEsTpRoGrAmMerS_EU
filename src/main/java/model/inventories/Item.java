package model.inventories;

import model.effects.IEffect;
import model.effects.IEffectContainer;
import model.entities.IPuckemon;

import java.util.List;

public class Item implements IEffectContainer {
    private List<IEffect> effects;

    private String name;
    private int prio;
    private String desc;
    private int quant;
    private int dmg;
    private int heal;


    public Item(String name, String desc, int prio, int quant, int dmg, int heal){
        this.name = name;
        this.desc = desc;
        this.prio = prio;
        this.quant = quant;
        this.dmg = dmg;
        this.heal = heal;
    }

    public String getName(){
        return this.name;
    }

    public String getDesc(){
        return this.desc;
    }

    public int getDmg(){
        return this.dmg;
    }

    public int getHeal(){
        return this.heal;
    }

    public int getQuant(){
        return this.quant;
    }

    @Override
    public int getPriority(){
        return this.prio;
    }

    @Override
    public List<IEffect> getEffects() {
        return effects;
    }

    @Override
    public void execute(IPuckemon user, IPuckemon opponent) {
        for (IEffect effect: effects) {
            effect.execute(user, opponent);
        }
    }

}
