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
        invList.clear();
    }

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

    public void decrementItemAmount(Item item){
        if(item.getQuantity() == 1){
            this.invList.remove(item);
        }else{
            item.setQuantity(item.getQuantity() - 1);
        }
    }

    public Item getItem(int i){
        return invList.get(i);
    }

    public int getInventorySize(){
        return invList.size();
    }

    public List<Item> getInventory(){
        return invList;
    }

}
