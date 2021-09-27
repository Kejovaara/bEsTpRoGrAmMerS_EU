package model.inventories;

import java.util.ArrayList;

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

    public void deleteItem(Item item){

    }

    public Item getItem(int x){
        return null;
    }

    public Inventory displayInventory(){

        return null;
    }

}
