package helloworld.inventories;

import helloworld.entities.Puckemon;
import java.util.ArrayList;


public class PuckeBag {
    private Puckemon puckemon = new Puckemon();

    private ArrayList<Puckemon> puckemons = new ArrayList<Puckemon>();

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
