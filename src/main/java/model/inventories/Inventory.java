package model.inventories;

import java.util.ArrayList;
import java.util.Arrays;

public class Inventory {

    ArrayList<Item> invList = new ArrayList<Item>();

    public Inventory(ArrayList<Item> invList){ // CREATE INVENTORY WITH EXISTING LIST
    }

    public Inventory(){ // CREATE EMPTY INVENTORY
        ArrayList<Item> invList = new ArrayList<Item>();
    }

    public void addItem(Item item){
        this.invList.add(item);
    }

    public void clearInventory(){
        int size = this.invList.size();
        for(int i = 0; i < size; i++){
            this.invList.set(i, null);
        }
    }

    public void deleteItem(int i){
        this.invList.set(i, null);
    }

    public Item getItem(int i){
        return invList.get(i);
    }

    public ArrayList<Item> getInventory(){
        return invList;
    }

}
