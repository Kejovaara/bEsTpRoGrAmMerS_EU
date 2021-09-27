package model.inventories;

import model.effects.IEffect;
import model.effects.IEffectContainer;
import model.entities.IPuckemon;

import java.util.List;

public class Item implements IEffectContainer {
    private int priority;
    private List<IEffect> effects;

    private String itemName;
    private int amount;
    private String itemDescription;

    private int heal;
    private int buff;
    private int cure;
    private int healPct;

//    public Item(){
//        this.heal = 0;
//        this.buff = 0;
//        this.cure = 0;
//    }

    public Item(String name, int amount, String itemDescription, int heal, int buff, int cure, int healPct){
        this.itemName = name;
        this.amount = amount;
        this.itemDescription = itemDescription;
        this.heal = heal;
        this.buff = buff;
        this.cure = cure;
        this.healPct = healPct;
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
