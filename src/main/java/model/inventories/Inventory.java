package model.inventories;

import java.util.ArrayList;
import java.util.List;

/**
 * The class representing an inventory which can be used by the trainers in the game and the player him-/herself.
 * The inventory contains items that can be used in the game during and outside of combat.
 * @author André Kejovaara
 */
public class Inventory {

    private final List<Item> invList;

    /**
     * Creates a new inventory with an already existing list of items.
     * @param invList List of Items
     */
    public Inventory(List<Item> invList){ // CREATE INVENTORY WITH EXISTING LIST
        this.invList = invList;
    }

    /**
     * Creates a new empty inventory.
     */
    public Inventory(){ // CREATE EMPTY INVENTORY
        invList = new ArrayList<>();
    }

    /**
     * Clears the entire inventory.
     */
    public void clearInventory(){
        invList.clear();
    }

    /**
     * Adds an object of class Item to the inventory.
     * @param item Object of class Item.
     */
    public void addItem(Item item){
            boolean wasItemAdded = false;
            for(Item invItem : invList){
                if(item.getId() == invItem.getId()){
                    invItem.setQuantity(invItem.getQuantity()+1);
                    wasItemAdded = true;
                    break;
                }
            }
            if(!wasItemAdded){
                invList.add(item);
            }
    }

    /**
     * Decrements the quantity of an item, if quantity hits 0, it gets removed completely from the list.
     * @param item Object of class Item.
     */
    public void decrementItemAmount(Item item){
        if(item.getQuantity() == 1){
            this.invList.remove(item);
        }else{
            item.setQuantity(item.getQuantity() - 1);
        }
    }

    /**
     * Returns an item at a specified index.
     * @param i Given index.
     * @return an object of class Item at a specified index of the Inventory.
     */
    public Item getItem(int i){
        return invList.get(i);
    }

    /**
     * Returns the size of the Inventory.
     * @return the size of the Inventory.
     */
    public int getInventorySize(){
        return invList.size();
    }

    /**
     * Returns a list of items.
     * @return a list with objects of class Item.
     */
    public List<Item> getInventory(){
        return invList;
    }

}
