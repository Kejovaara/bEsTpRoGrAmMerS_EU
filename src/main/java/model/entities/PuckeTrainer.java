package model.entities;

import model.inventories.PuckeBag;

public class PuckeTrainer {
    private PuckeBag puckeBag = new PuckeBag(1, 1);

    public PuckeBag getPuckeBag() {
        return puckeBag;
    }
}
