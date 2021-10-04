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

    public void clearInventory(){
        int size = invList.size();
        for(int i = 0; i < size; i++){
            invList.set(i, null);
        }
    }

    public void addItem(Item item){
            if(invList.contains(item)){
                item.incrementAmount(1);
            }else{
                invList.add(item);
            }
    }

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
        return invList.get(i);
    }

    public int getInventorySize(){
        return  invList.size();
    }

    public ArrayList<Item> getInventory(){
        return invList;
    }

    public void openInventory(){
        //TODO METHOD TO OPEN INVENTORY
    }

    public void closeInventory(){
        //TODO METHOD TO CLOSE INVENTORY
    }
}
