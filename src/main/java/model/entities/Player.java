package model.entities;


import model.inventories.*;

public class Player {
    private PuckeBag puckeBag = new PuckeBag(1,1);
    private Inventory inventory = new Inventory();



    /**
     * Player choices
     */

    private void switchPuckemon(int index){
//        make sure that the player cant pick wrong target
        puckeBag.setActiveParty(index);
    }


    public PuckeBag getPuckeBag() {
        return puckeBag;
    }

    public Inventory getInventory() {return inventory;}

}
