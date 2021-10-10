package model.inventories;

import model.effects.IEffect;
import model.effects.IEffectContainer;
import model.entities.IPuckemon;

import java.util.ArrayList;
import java.util.List;

public class Item implements IEffectContainer {
    private List<IEffect> effects;

    private int prio, quantity, price;
    private final String name;
    private final String desc;
    private boolean isCombatItem;

    public Item(String name, String desc, int prio, int quantity, int price, boolean isCombatItem, List<IEffect> effects){
        this.name = name;
        this.desc = desc;
        this.prio = prio;
        this.quantity = quantity;
        this.price = price;
        this.isCombatItem = isCombatItem;
        this.effects = effects;
    }

    // Effectless items
    public Item(String name, String desc, int prio, int quantity, int price,boolean isCombatItem){
        this.name = name;
        this.desc = desc;
        this.prio = prio;
        this.quantity = quantity;
        this.price = price;
        this.isCombatItem = isCombatItem;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.desc;
    }


    public int getQuantity(){
        return this.quantity;
    }

    public int getValue(){
        return this.price;
    }

    public void incrementAmount(int num){
        this.quantity += num;
    }

    public void decrementAmount(int num){
        this.quantity -= num;
    }

    /*
    public static List<Item> stack(List<Item> items){
        //TODO METHOD FOR STACKING ITEMS
        List<Item> stackedItems = new ArrayList<Item>();
        for(Item i : items){
            //TODO
        }
        return stackedItems;
    }*/

    @Override
    public int getPriority(){
        return prio;
    }

    @Override
    public List<IEffect> getEffects() {
        return effects;
    }

    @Override
    public void execute(IPuckemon user, IPuckemon opponent) {
        decrementAmount(1);
        for (IEffect effect: effects) {
            effect.execute(user, opponent);
        }
    }
}
