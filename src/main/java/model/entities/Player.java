package model.entities;


import model.effects.IEffectContainer;
import model.inventories.*;

import java.util.ArrayList;

public class Player implements ITrainer {
    private String name = "Bamse";
    private PuckeBag puckeBag = new PuckeBag(1,1);
    private Inventory inventory = new Inventory();

    /**
     * Player choices
     */


    public void switchPuckemon(int index){
//        make sure that the player cant pick wrong target
        puckeBag.setActiveParty(index);
    }



    public IEffectContainer getItem(int index) {
        return inventory.getItem(index);
    }

    public void addItem(Item item){ inventory.addItem(item);}

    public PuckeBag getPuckeBag() {
        return puckeBag;
    }

    public Inventory getInventory() {return inventory;}

}
