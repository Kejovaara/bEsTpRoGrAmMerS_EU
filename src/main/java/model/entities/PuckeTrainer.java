package model.entities;

import model.combat.Attack;
import model.effects.IEffectContainer;
import model.inventories.Inventory;
import model.inventories.Item;
import model.inventories.PuckeBag;

import java.util.ArrayList;
import java.util.Random;

public class PuckeTrainer implements IFighter {
    private String name;
    private PuckeBag puckeBag = new PuckeBag(1, 1);
    private Inventory inventory;

    public PuckeTrainer(String name, ArrayList<Puckemon> puckemons, ArrayList<Item> items){
        this.name = name;
        this.puckeBag = new PuckeBag(puckemons);
        this.inventory = new Inventory(items);
    }

    public Puckemon selectPuckemon(){
        Puckemon puckemon = puckeBag.getNextPuckemon();
        return puckemon;
    }

    public IEffectContainer getMoves(Player player) {
        Random rand = new Random(); //instance of random class
        int upperbound = 4;
        //generate random values from 0-3
        int int_random = rand.nextInt(upperbound);

        return puckeBag.getNextPuckemon().getAttack(int_random);
    }

    public void addItem(Item item){ inventory.addItem(item);}
}
