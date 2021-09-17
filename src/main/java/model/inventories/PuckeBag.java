package model.inventories;

import model.entities.Puckemon;
import java.util.ArrayList;


public class PuckeBag {
    private Puckemon puckemon = new Puckemon();
    private ArrayList<Puckemon> puckemons;

    public PuckeBag(ArrayList<Puckemon> puckemons){
        this.puckemons = puckemons;
    }

    public void add(Puckemon puckemon) {
        puckemons.add(puckemon);
    }
    public Puckemon getNextPuckemon() {
        return puckemons.get(0);
    }
}
