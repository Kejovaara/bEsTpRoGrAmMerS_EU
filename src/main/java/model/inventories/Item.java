package model.inventories;

import model.effects.IEffect;
import model.effects.IEffectContainer;
import model.puckemon.IPuckemon;

import java.util.List;

/**
 * A class for handling how items work in the game.
 * @author André Kejovaara
 */
public class Item implements IEffectContainer {

    private List<IEffect> effects;

    private final int id, prio, price;
    private int quantity;
    private final String name;
    private final String desc;

    /**
     * Constructor for object Item
     * @param id id of object Item
     * @param name name of object Item
     * @param desc description of object Item
     * @param prio priority of object Item
     * @param quantity amount of object Item
     * @param price cost of object Item
     * @param effects list of Effects the item has
     */
    public Item(int id,String name, String desc, int prio, int quantity, int price, List<IEffect> effects){
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.prio = prio;
        this.quantity = quantity;
        this.price = price;
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
     */
    //CONSTRUCTOR FOR EFFECTLESS ITEMS
    public Item(int id,String name, String desc, int prio, int quantity, int price){
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.prio = prio;
        this.quantity = quantity;
        this.price = price;
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
     * @return the description of the item.
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
     * @see IEffectContainer
     */
    @Override
    public int getPriority(){
        return prio;
    }

    /**
     * @see IEffectContainer
     */
    @Override
    public List<IEffect> getEffects() {
        return effects;
    }

    /**
     * @see  IEffectContainer
     */
    @Override
    public void execute(IPuckemon user, IPuckemon opponent) {
        setQuantity(getQuantity()-1);
        for (IEffect effect: effects) {
            effect.execute(user, opponent);
        }
    }
}
