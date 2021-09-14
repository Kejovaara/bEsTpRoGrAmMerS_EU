package model.entities;


import model.inventories.*;

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
