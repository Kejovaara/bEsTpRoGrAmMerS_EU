package model.entities;

import model.effects.IEffectContainer;
import model.inventories.*;

import java.util.ArrayList;

public class Player implements ITrainer {
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

    public void selectMoves(int index) {
        selectPuckemon().getAttack(0);
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
