package model.inventories;

import model.effects.IEffect;
import model.effects.IEffectContainer;
import model.entities.IPuckemon;

import java.util.ArrayList;
import java.util.List;

/**
 * A class for handling how items work in the game.
 * @author André Kejovaara
 */
public class Item implements IEffectContainer {

    private List<IEffect> effects;

    private int id, prio, quantity, price;
    private final String name;
    private final String desc;
    private boolean isCombatItem;

    /**
     * Constructor for object Item
     * @param id id of object Item
     * @param name name of object Item
     * @param desc description of object Item
     * @param prio priority of object Item
     * @param quantity amount of object Item
     * @param price cost of object Item
     * @param isCombatItem weather or not item is an item to be used in combat
     * @param effects list of Effects the item has
     */
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

    /**
     * Constructor for effectless items
     * @param id id of object Item
     * @param name name of object Item
     * @param desc description of object Item
     * @param prio priority of object Item
     * @param quantity amount of object Item
     * @param price cost of object Item
     * @param isCombatItem weather or not item is an item to be used in combat
     */
    //CONSTRUCTOR FOR EFFECTLESS ITEMS
    public Item(int id,String name, String desc, int prio, int quantity, int price, boolean isCombatItem){
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.prio = prio;
        this.quantity = quantity;
        this.price = price;
        this.isCombatItem = isCombatItem;
    }

    /**
     * Calls the id of the item
     * @return the id of the item
     */
    public int getId(){ return id; }

    /**
     * returns the name of the item.
     * @return the name of the item.
     */
    public String getName(){
        return name;
    }

    /**
     * returns the description of the item.
     * @return
     */
    public String getDescription(){
        return desc;
    }

    /**
     * returns the amount of a certain item
     * @return the amount of a certain item
     */
    public int getQuantity(){
        return this.quantity;
    }

    /**
     * sets the amount of a specified item.
     * @param a the wished amount to be set for the specified item.
     */
    public void setQuantity(int a){
        this.quantity = a;
    }

    /**
     * returns the price of the specified item.
     * @return the price of the specified item.
     */
    public int getValue(){
        return this.price;
    }

    /**
     * see JavaDoc of IEffectContainer
     */
    @Override
    public int getPriority(){
        return prio;
    }

    /**
     * see JavaDoc of IEffectContainer
     */
    @Override
    public List<IEffect> getEffects() {
        return effects;
    }

    /**
     * see JavaDoc of IEffectContainer
     */
    @Override
    public void execute(IPuckemon user, IPuckemon opponent) {
        setQuantity(getQuantity()-1);
        for (IEffect effect: effects) {
            effect.execute(user, opponent);
        }
    }
}
