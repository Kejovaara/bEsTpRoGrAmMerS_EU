package model.entities;


import model.inventories.*;

public class Player {
    private PuckeBag puckeBag = new PuckeBag(1,1);
    private Inventory inventory = new Inventory();



    /**
     * Player choices
     */

    private void switchPuckemon(){

    }


    public PuckeBag getPuckeBag() {
        return puckeBag;
    }

    public Inventory getInventory() {return inventory;}

}
