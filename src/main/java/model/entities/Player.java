package model.entities;

import model.effects.IEffectContainer;
import model.inventories.*;

import java.util.ArrayList;

public class Player implements ITrainer {
    private String name = "Bamse";
    private PuckeBag puckeBag;
    private Inventory inventory;
    private int coins;

    public Player(ArrayList<Puckemon> puckemons, ArrayList<Item> items, int coins){
        this.puckeBag = new PuckeBag(puckemons);
        this.inventory = new Inventory(items);
        this.coins = coins;
    }

    public Puckemon switchPuckemon(int index){
        puckeBag.setActivePuckemon(index);
        return puckeBag.getActivePuckemon();
    }

    public void selectMoves(int index) {
        puckeBag.getActivePuckemon().getAttack(0);
    }


    public IEffectContainer getItem(int index) {
        return inventory.getItem(index);
    }

    public void addItem(Item item){
        this.inventory.addItem(item);
    }

    public void useItem(Item item){
        // item.execute(this);
    }

    public void buyItem(Item item){
        if(this.coins >= item.getValue()){
            this.coins -= item.getValue();
            inventory.addItem(item);
        }else{
            System.out.println("You don't have enough PuckeCoins for this item!");
        }
    }

    public void addPuckemon(Puckemon puckemon) {
        puckeBag.(puckemon);
    }
}
