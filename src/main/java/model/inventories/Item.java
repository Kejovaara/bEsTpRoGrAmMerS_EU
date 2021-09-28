package model.inventories;

import model.effects.IEffect;
import model.effects.IEffectContainer;
import model.entities.IPuckemon;

import java.util.List;

public class Item implements IEffectContainer {
    private List<IEffect> effects;
    private List<Item> itemList;

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

    public String getDesc(){
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

    public void add(int num){
        quantity += num;
    }

    public int getPrice(){
        return price;
    }

    public static List<Item> stack(List<Item> items){
        //TODO METHOD FOR STACKING ITEMS
        return null;
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
