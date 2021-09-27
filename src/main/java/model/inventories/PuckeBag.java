package model.inventories;

import model.entities.Puckemon;
import java.util.ArrayList;


public class PuckeBag {
    private Puckemon puckemon = new Puckemon(1, 1);

    private ArrayList<Puckemon> puckemons = new ArrayList<Puckemon>();

    public PuckeBag(ArrayList<Puckemon> puckemons){
        this.puckemons = puckemons;
    }

    public void add(Puckemon puckemon) {
        puckemons.add(puckemon);
    }

    public ArrayList<Puckemon> getPuckemons() {
        return puckemons;
    }
    public Puckemon getNextPuckemon() {
        return puckemons.get(0);
    }
}
