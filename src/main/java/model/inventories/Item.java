package model.inventories;

import model.effects.IEffect;
import model.effects.IEffectContainer;
import model.entities.IPuckemon;

import java.util.ArrayList;
import java.util.List;

public class Item implements IEffectContainer {

    private List<IEffect> effects;

    private int id, prio, quantity, price;
    private final String name;
    private final String desc;
    private boolean isCombatItem;

    public Item(int id,String name, String desc, int prio, int quantity, int price, boolean isCombatItem, List<IEffect> effects){
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.prio = prio;
        this.quantity = quantity;
        this.price = price;
        this.isCombatItem = isCombatItem;
        this.effects = effects;
    }

    // Effectless items
    public Item(int id, String name, String desc, int prio, int quantity, int price,boolean isCombatItem){
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.prio = prio;
        this.quantity = quantity;
        this.price = price;
        this.isCombatItem = isCombatItem;
    }

    public int getId(){ return this.id; }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.desc;
    }


    public int getQuantity(){
        return this.quantity;
    }

    public void setQuantity(int a){
        this.quantity = a;
    }

    public int getValue(){
        return this.price;
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
        setQuantity(getQuantity()-1);
        /*Inventory i = new Inventory();
        i.decrementItemAmount(this);*/
        for (IEffect effect: effects) {
            effect.execute(user, opponent);
        }
    }
}
