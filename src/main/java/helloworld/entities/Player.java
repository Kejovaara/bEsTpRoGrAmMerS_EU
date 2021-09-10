package helloworld.entities;

import helloworld.inventories.*;

public class Player {
    private PuckeBag puckeBag = new PuckeBag();
    private Inventory inventory = new Inventory();

    public PuckeBag getPuckeBag() {
        return puckeBag;
    }
    public Inventory getInventory() {return inventory;}

}
