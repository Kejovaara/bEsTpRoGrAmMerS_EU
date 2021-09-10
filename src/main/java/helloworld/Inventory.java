package helloworld;

import java.util.ArrayList;

public class Inventory {

    ArrayList<Item> inventory = new ArrayList<>();

    public Inventory(){
        Item rock = new Item("Rock", 1, ItemEffects.THROWROCK, "An apple sized rock");
        inventory.add(rock);
    }

}
