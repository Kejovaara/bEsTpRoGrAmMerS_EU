package model.entities;


import model.combat.Attack;
import model.inventories.*;

import java.util.ArrayList;

public class Player {
    private String name = "Player";
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

    public void selectMove(int index) {
        selectPuckemon().makeMove(0);
    }

    public void addItem(Item item){ inventory.addItem(item);}

    public void addPuckemon(Puckemon puckemon) {
        puckeBag.add(puckemon);
    }
}
