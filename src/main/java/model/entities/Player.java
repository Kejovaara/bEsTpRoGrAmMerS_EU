package model.entities;

import model.effects.IEffectContainer;
import model.entities.puckemon.OwnedPuckemon;
import model.entities.puckemon.Puckemon;
import model.inventories.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A class representing the player meant to function as a trainer in the game.
 * @author Emil Jonsson
 */
public class Player implements ITrainer {
    private String name = "Bamse";
    private final PlayerPuckeBag playerPuckeBag;
    private final Inventory inventory;
    private int coins;

    /**
     * Constructor for creating a player that already has an inventory to be sent with the new player.
     * @param puckemons list of Puckemons to add to the trainers puckeBag
     * @param inventory a class containing items
     * @param coins amount of coins
     */
    public Player(List<OwnedPuckemon> puckemons, Inventory inventory, int coins){
        this.playerPuckeBag = new PlayerPuckeBag(puckemons);
        this.inventory = inventory;
        this.coins = coins;
    }

    /**
     * Constructor for Player.
     * @param puckemons list of Puckemons to add to the trainers puckeBag
     * @param coins amount of coins
     */
    public Player(List<OwnedPuckemon> puckemons,  int coins){
        this.playerPuckeBag = new PlayerPuckeBag(puckemons);
        this.inventory = new Inventory();
        this.coins = coins;
    }

    /**
     * Switches Puckemon to the specified index in the PlayerBag.
     * @param index the index where the Puckemon lies in the PlayerBag
     */
    public void switchPuckemon(int index){
        playerPuckeBag.switchPuckemon(index);
    }

    // Get Mons moveSet
    public void selectMoves(int index) {
        playerPuckeBag.getActivePuckemon().getAttack(index);
    }

    /**
     * Returns the Item at the specified index in the Inventory
     * @param index the index where the Item lies in the inventory
     * @return the sought after Item
     */
    public IEffectContainer getItem(int index) {
        return inventory.getItem(index);
    }

    /**
     * Adds an item to the inventory.
     * @param item the item to be added to the Inventory.
     */
    public void addItem(Item item){
        this.inventory.addItem(item);
    }

    /**
     * Consumes an item so it disappears.
     * @param index the index of the item to be consumed.
     */
    public void consumeItem(int index){
        inventory.decrementItemAmount(inventory.getItem(index));
    }

    //Currently unimplemented
    /**
     * Method for buying items of a vendor
     * @param item the item to be bought and added to the player inventory.
     */
    public void buyItem(Item item){
        if(this.coins >= item.getValue()){
            this.coins -= item.getValue();
            inventory.addItem(item);
        }else{
            System.out.println("You don't have enough PuckeCoins for this item!");
        }
    }

    /**
     * Method for generating an inventory with a random amount of items.
     * @param maxAmount the maximum amount of items to be added to the starting inventory.
     */
    public void generateStartingInventory(int maxAmount){
        int randomAmount = ThreadLocalRandom.current().nextInt(1, maxAmount + 1);
        for(int i = 0; i < randomAmount; i++){
            addItem(ItemBuilder.getRandom());
        }
    }

    //FOR DEVELOPMENT PURPOSES ONLY
    /**
     * Adds a set amount of items to inventory
     * @param amount the amount of items to be added to the inventory.
     */
    public void generateStartingInventoryDEV(int amount){
        for(int i = 0; i < amount; i++){
            addItem(ItemBuilder.getRandom());
        }
    }

    /**
     * Returns a copy of playerBags party
     * @return the players party of puckemons
     */
    public List<OwnedPuckemon> getParty(){
        return playerPuckeBag.getParty();
    }

    /**
     * Returns a copy of the inventory
     * @return the inventory list
     */
    public List<Item> getInventory(){
        return new ArrayList<>(inventory.getInventory());
    }


    /**
     * Triggers the victory event, meant to handle the puckemons after victory (giving them xp)
     */
    public void victoryEvent(){
        playerPuckeBag.afterVictory();
    }

    /**
     * Checks if the player is defeated, used to end combat
     * @return true if defeated, false if not
     */
    public boolean checkIfDefeated(){
        boolean defeated = true;
        for (int i = 0; i < playerPuckeBag.getParty().size(); i++) {
            if(playerPuckeBag.getParty().get(i).getHealth() >0){
                defeated = false;
                break;
            }
        }
        return defeated;
    }

    /**
     * Returns the Puckemon currently fighting
     * @return active puckemon
     */
    public Puckemon getActivePuckemon(){
        return playerPuckeBag.getActivePuckemon();
    }
}
