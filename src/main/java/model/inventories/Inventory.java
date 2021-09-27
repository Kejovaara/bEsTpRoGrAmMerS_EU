package model.inventories;

import model.effects.IEffectContainer;
import model.entities.Puckemon;

import java.util.ArrayList;

public class Inventory {

    ArrayList<Item> items;

    public Inventory(ArrayList<Item> items){
        this.items = items;
//        Item xAttack = new Item("xAttack", 1, "Increases Attack", 0, 0, 0, 0);
//        Item potion = new Item("Potion", 1, "Restores 20 hp", 20, 0, 0, 0);
//        Item fullHeal = new Item("fullHeal",1,"Restores HP to max", 0,0,0,100);
//        inventory.add(xAttack);
//        inventory.add(potion);
//        inventory.add(fullHeal);
    }

    public void addItem(Item item){ items.add(item);}

    public IEffectContainer getItem(int index) {
        return items.get(index);
    }

}
