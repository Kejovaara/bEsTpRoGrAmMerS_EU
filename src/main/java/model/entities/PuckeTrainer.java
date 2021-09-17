package model.entities;

import model.combat.Attack;

import model.inventories.Inventory;
import model.inventories.Item;
import model.inventories.PuckeBag;

import java.util.ArrayList;

public class PuckeTrainer implements IOpponent {
    private String name;
    private PuckeBag puckeBag;
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

    public void makeMove(Player player) {
        selectPuckemon().makeMove(0);
    }

    public void addItem(Item item){ inventory.addItem(item);}
}
