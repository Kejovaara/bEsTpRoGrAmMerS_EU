package helloworld.entities;


import helloworld.inventories.*;
import helloworld.combat.ICombat;

public class Player {
    private PuckeBag puckeBag = new PuckeBag();
    private Inventory inventory = new Inventory();

    public void addPuckemon(Puckemon puckemon) {
        puckeBag.add(puckemon);
    }

    public PuckeBag getPuckeBag() {
        return puckeBag;
    }

    public Inventory getInventory() {return inventory;}

}
