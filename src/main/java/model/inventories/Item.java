package model.inventories;

import model.effects.IEffect;
import model.effects.IEffectContainer;
import model.entities.IPuckemon;

import java.util.ArrayList;
import java.util.List;

public class Item implements IEffectContainer {
    private List<IEffect> effects;
    // private List<Item> itemList; //MAYBE NOT NEEDED

    private int id, prio, quantity, dmg, heal, price;
    private String name;
    private String desc;

    public Item(String name, String desc, int prio, int quant, int dmg, int heal, int price){
        this.name = name;
        this.desc = desc;
        this.prio = prio;
        this.quantity = quant;
        this.dmg = dmg;
        this.heal = heal;
        this.price = price;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return desc;
    }

    public int getDamage(){
        return dmg;
    }

    public int getHeal(){
        return heal;
    }

    public int getQuantity(){
        return quantity;
    }

    public int getPrice(){
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
