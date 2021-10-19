package model.entities;

import model.effects.IEffectContainer;
import model.inventories.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Player implements ITrainer, IFighter {
    private String name = "Bamse";
    private PuckeBag puckeBag;
    private Inventory inventory;
    private int coins;

    public Player(List<OwnedPuckemon> puckemons, Inventory inventory, int coins){
        this.puckeBag = new PuckeBag(puckemons);
        this.inventory = inventory;
        this.coins = coins;
    }

    public Player(List<OwnedPuckemon> puckemons,  int coins){
        this.puckeBag = new PuckeBag(puckemons);
        this.inventory = new Inventory();
        this.coins = coins;
    }

    // Pick target in party to switch too
    public void switchPuckemon(int index){
        puckeBag.setActivePuckemon(index);
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

    public void consumeItem(int index){
        inventory.decrementItemAmount(inventory.getItem(index));
    }

    public void buyItem(Item item){
        if(this.coins >= item.getValue()){
            this.coins -= item.getValue();
            inventory.addItem(item);
        }else{
            System.out.println("You don't have enough PuckeCoins for this item!");
        }
    }

    public void generateStartingInventory(int maxAmount){
        int randomAmount = ThreadLocalRandom.current().nextInt(1, maxAmount + 1);
        for(int i = 0; i < randomAmount; i++){
            addItem(ItemFactory.getRandom());
        }
    }

    public void generateStartingInventoryDEV(int amount){
        for(int i = 0; i < amount; i++){
            addItem(ItemFactory.getRandom());
        }
    }

    public List<Puckemon> getParty(){
        return puckeBag.getParty();
    }

    public List<Item> getInventory(){
        return inventory.getInventory();
    }

    private void addPuckemonToParty(Puckemon puckemon) {
        puckeBag.addPuckemonToParty(puckemon);
    }

    @Override
    public IEffectContainer makeMove(IPuckemon p) {
        return null;
    }

    @Override
    public IPuckemon getActivePuckemon() {
        return null;
    }

    public Puckemon getPuckemon(){
        return puckeBag.getActivePuckemon();
    }
}
