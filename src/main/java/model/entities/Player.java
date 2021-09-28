package model.entities;

import model.CombatOptions;
import model.effects.IEffectContainer;
import model.inventories.*;

import java.util.ArrayList;
import java.util.Random;

public class Player implements ITrainer, IFighter {
    private String name = "Bamse";
    private PuckeBag puckeBag;
    private Inventory inventory;

    public Player(ArrayList<Puckemon> puckemons, ArrayList<Item> items){
        this.puckeBag = new PuckeBag(puckemons);
        this.inventory = new Inventory(items);
    }

    public Puckemon selectPuckemon(){
        Puckemon puckemon = puckeBag.getNextPuckemon();
        return puckemon;
    }

    public IEffectContainer getMoves() {
        Random rand = new Random(); //instance of random class
        int upperbound = 4;
        //generate random values from 0-3
        int int_random = rand.nextInt(upperbound);

        return puckeBag.getNextPuckemon().getAttack(int_random);
    }

    public CombatOptions getOptions() {
        //Choose option
        return CombatOptions.EFFECT;
    }


    public void switchPuckemon(int index) {

    }

    public IEffectContainer getItem(int index) {
        return inventory.getItem(index);
    }

    public void addItem(Item item){ inventory.addItem(item);}

    public void addPuckemon(Puckemon puckemon) {
        puckeBag.add(puckemon);
    }
}
