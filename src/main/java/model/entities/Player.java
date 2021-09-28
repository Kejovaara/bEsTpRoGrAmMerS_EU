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

    private void switchPuckemon(int index){
//        make sure that the player cant pick wrong target
        puckeBag.setActiveParty(index);
    }

    public Puckemon selectPuckemon(){
        Puckemon puckemon = puckeBag.getNextPuckemon();
        return puckemon;
    }

    public void selectMoves(int index) {
        selectPuckemon().getAttack(0);
    }

    public void switchPuckemon(int index) {

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
