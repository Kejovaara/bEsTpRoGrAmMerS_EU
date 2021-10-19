package model.entities;

import model.effects.IEffectContainer;
import model.inventories.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Player implements ITrainer, IFighter {
    private String name = "Bamse";
    private PlayerBag playerBag;
    private Inventory inventory;
    private int coins;

    public Player(List<OwnedPuckemon> puckemons, Inventory inventory, int coins){
        this.playerBag = new PlayerBag(puckemons);
        this.inventory = inventory;
        this.coins = coins;
    }

    public Player(List<OwnedPuckemon> puckemons,  int coins){
        this.playerBag = new PlayerBag(puckemons);
        this.inventory = new Inventory();
        this.coins = coins;
    }

    // Pick target in party to switch too
    public void switchPuckemon(int index){
        playerBag.setActivePuckemon(index);
    }

    // Get Mons moveSet
    public void selectMoves(int index) {
        playerBag.getActivePuckemon().getAttack(index);
    }


    public IEffectContainer getItem(int index) {
        return inventory.getItem(index);
    }

    public void addItem(Item item){
        this.inventory.addItem(item);
    }

    public void consumeItem(int index){
        inventory.getItem(index).decrementAmount(1);
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

    public List<OwnedPuckemon> getParty(){
        return playerBag.getParty();
    }

    public List<Item> getInventory(){
        return inventory.getInventory();
    }

    public void victoryEvent(){
        playerBag.giveVictoryRewards();
    }

    @Override
    public boolean checkIfDefeated(){
        boolean defeated = true;
        for (int i = 0; i < playerBag.getParty().size(); i++) {
            if(playerBag.getParty().get(i).currentHealth >0){
                defeated = false;
                break;
            }
        }
        return defeated;
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
        return playerBag.getActivePuckemon();
    }
}
