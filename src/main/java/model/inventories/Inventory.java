package model.inventories;

import java.util.ArrayList;

public class Inventory {

    ArrayList<Item> invList = new ArrayList<Item>();

    public Inventory(ArrayList<Item> invList){ // CREATE INVENTORY WITH EXISTING LIST
        this.invList = invList;
    }

    public Inventory(){ // CREATE EMPTY INVENTORY
        ArrayList<Item> invList = new ArrayList<Item>();
        this.invList = invList;
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
        return this.invList.get(i);
    }

    public ArrayList<Item> getInventory(){
        return this.invList;
    }

}
