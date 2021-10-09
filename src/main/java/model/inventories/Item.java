package model.inventories;

import model.effects.IEffect;
import model.effects.IEffectContainer;
import model.entities.IPuckemon;

import java.util.ArrayList;
import java.util.List;

public class Item implements IEffectContainer {

    enum Type{
        CONSUMABLE,
        MERCHANTS,
        MISC
    }

    private List<IEffect> effects;
    // private List<Item> itemList; //MAYBE NOT NEEDED

    private final int id, prio, quantity, price;
    private final String name;
    private final String desc;

    public Item(int id, String name, String desc, Type type, int prio, int quantity, int price){
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.prio = prio;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return desc;
    }

    public int getQuantity(){
        return quantity;
    }

    public int getValue(){
        return price;
    }

    public void incrementAmount(int num){
        quantity += num;
    }

    public void decrementAmount(int num){
        quantity -= num;
    }

    //NOT DONE
    public static List<Item> stack(List<Item> items){
        //TODO METHOD FOR STACKING ITEMS
        List<Item> stackedItems = new ArrayList<Item>();
        for(Item i : items){
            //TODO
        }
        return stackedItems;
    }

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
        for (IEffect effect: effects) {
            effect.execute(user, opponent);
        }
    }

}
