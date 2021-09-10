package helloworld.entities;

import helloworld.inventories.PuckeBag;

public class Player {
    private PuckeBag puckeBag = new PuckeBag();

    public PuckeBag getPuckeBag() {
        return puckeBag;
    }

}
