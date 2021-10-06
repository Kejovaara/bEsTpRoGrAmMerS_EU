package model.entities;

import model.effects.IEffectContainer;
import model.inventories.*;

import java.util.ArrayList;
import java.util.List;

public class Player implements ITrainer, IFighter {
    private String name = "Bamse";
    private PuckeBag puckeBag;
    private Inventory inventory;
    private int coins;

    public Player(List<OwnedPuckemon> puckemons, List<Item> items, int coins){
        this.puckeBag = new PuckeBag(puckemons);
        this.inventory = new Inventory(items);
        this.coins = coins;
    }

    public Player(List<OwnedPuckemon> puckemons,  int coins){
        this.puckeBag = new PuckeBag(puckemons);
        this.inventory = new Inventory();
        this.coins = coins;
    }

    // Pick target in party to switch too
    public Puckemon switchPuckemon(int index){
        puckeBag.setActivePuckemon(index);
        return puckeBag.getActivePuckemon();
    }

    // Get Mons moveSet
    public void selectMoves(int index) {
        puckeBag.getActivePuckemon().getAttack(index);
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

    public List<Puckemon> getParty(){
        return puckeBag.getParty();
    }

    private void addPuckemonToParty(Puckemon puckemon) {
        puckeBag.addPuckemonToParty(puckemon);
    }

    private void addPuckemonToBox(Puckemon puckemon){
        addPuckemonToBox(puckemon);
    }

    @Override
    public IEffectContainer makeMove() {
        return null;
    }

    @Override
    public IPuckemon getActivePuckemon() {
        return null;
    }
}
