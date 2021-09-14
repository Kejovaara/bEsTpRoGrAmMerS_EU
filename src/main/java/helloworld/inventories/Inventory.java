package helloworld.inventories;

import java.util.ArrayList;

public class Inventory {

    ArrayList<Item> inventory = new ArrayList<>();

    public Inventory(){
        Item xAttack = new Item("xAttack", 1, "Increases Attack", 0, 0, 0, 0);
        Item potion = new Item("Potion", 1, "Restores 20 hp", 20, 0, 0, 0);
        Item fullHeal = new Item("fullHeal",1,"Restores HP to max", 0,0,0,100);
        inventory.add(xAttack);
        inventory.add(potion);
        inventory.add(fullHeal);
    }

}
