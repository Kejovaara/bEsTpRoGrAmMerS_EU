package model.inventories;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Inventory {

    private List<Item> invList;

    public Inventory(List<Item> invList){ // CREATE INVENTORY WITH EXISTING LIST
        this.invList = invList;
    }

    public Inventory(){ // CREATE EMPTY INVENTORY
        invList = new ArrayList<Item>();
    }

    public void clearInventory(){
        this.invList.clear();
    }

    public void addItem(Item item){
            boolean wasItemAdded = false;
            for(Item invItem : invList){
                if(item.getId() == invItem.getId()){
                    invItem.incrementAmount(1);
                    wasItemAdded = true;
                    break;
                }
            }
            if(!wasItemAdded){
                invList.add(item);
            }
    }
    //TODO : When item quantity == 0, remove object from list

    public void deleteItemStack(int i){
        invList.set(i, null);
    }

    public void deleteSingleItem(Item item){
        if(invList.contains(item)){
            item.decrementAmount(1);
        }else{
            System.out.println("Item does not exist");
        }

    }

    public Item getItem(int i){
        return this.invList.get(i);
    }

    public int getInventorySize(){
        return  this.invList.size();
    }

    public List<Item> getInventory(){
        return this.invList;
    }

}
