package helloworld.entities;

import helloworld.inventories.PuckeBag;

public class Player {
    private PuckeBag puckeBag = new PuckeBag();
    private Inventory inventory = new Inventory();

    public PuckeBag getPuckeBag() {
        return puckeBag;
    }

}
