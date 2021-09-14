package helloworld.entities;

import helloworld.combat.ICombat;
import helloworld.inventories.PuckeBag;

public class PuckeTrainer implements ICombat {
    private PuckeBag puckeBag = new PuckeBag();

    public PuckeBag getPuckeBag() {
        return puckeBag;
    }
}
