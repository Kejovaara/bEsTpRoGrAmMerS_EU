package helloworld.entities;

import helloworld.combat.ICombat;
import helloworld.inventories.PuckeBag;

public class Player implements ICombat {
    private PuckeBag puckeBag = new PuckeBag();

    public void addPuckemon(Puckemon puckemon) {
        puckeBag.add(puckemon);
    }

    public PuckeBag getPuckeBag() {
        return puckeBag;
    }
}
