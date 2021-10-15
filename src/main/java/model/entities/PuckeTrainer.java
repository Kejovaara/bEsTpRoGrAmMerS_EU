package model.entities;

import model.attack.AttackFactory;
import model.effects.IEffectContainer;
import model.inventories.Inventory;
import model.inventories.Item;
import model.inventories.PuckeBag;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PuckeTrainer implements IFighter {
    private String name;
    private PuckeBag puckeBag;
    private Inventory inventory;

    public PuckeTrainer(String name, List<OwnedPuckemon> puckemons, List<Item> items){
        this.name = name;
        this.puckeBag = new PuckeBag(puckemons);
        this.inventory = new Inventory(items);
    }

    public PuckeTrainer(String name, List<OwnedPuckemon> puckemons){
        this.name = name;
        this.puckeBag = new PuckeBag(puckemons);
        this.inventory = new Inventory();
    }

//    public Puckemon selectPuckemon(){
//        Puckemon puckemon = puckeBag.getNextPuckemon();
//        return puckemon;
//    }

public IEffectContainer makeMove() {
        Random rand = new Random(); //instance of random class
       int upperbound = puckeBag.getActivePuckemon().getMoveSet().size();
       //generate random values from 0-3
        int int_random = rand.nextInt(upperbound);

        //TODO: Fix so that it gets random attack
        return puckeBag.getActivePuckemon().getAttack(int_random);
        //return AttackFactory.getTackle();
    }

    @Override
    public IPuckemon getActivePuckemon() {
        return puckeBag.getActivePuckemon();
    }

    public Puckemon getPuckemon(){
        return puckeBag.getActivePuckemon();
    }

    public void addItem(Item item){ inventory.addItem(item);}

    public void useItem(Item item){
        // item.execute();
    }
}
