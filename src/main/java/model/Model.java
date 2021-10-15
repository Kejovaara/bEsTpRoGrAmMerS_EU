package model;

import model.attack.Attack;
import model.combat.Combat;
import model.entities.*;
import model.inventories.Item;
import model.inventories.ItemFactory;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private CreatePuckemon createPuckemon = new CreatePuckemon();

    private List<OwnedPuckemon> playerList = new ArrayList<>();
    private List<OwnedPuckemon> trainerList = new ArrayList<>();

    private Player player;
    private PuckeTrainer trainer;
    private Combat combat;

    public Model() {
        playerList.add(createPuckemon.createOwnedPuckemon(128,50));
        playerList.add(createPuckemon.createOwnedPuckemon(1,2));
        playerList.add(createPuckemon.createOwnedPuckemon(2,4));
        playerList.add(createPuckemon.createOwnedPuckemon(3,50));
        playerList.add(createPuckemon.createOwnedPuckemon(5,30));

        trainerList.add(createPuckemon.createOwnedPuckemon(3,50));
        trainerList.add(createPuckemon.createOwnedPuckemon(5,20));

        player = new Player(playerList, 10);
        trainer = new PuckeTrainer("Bertil the great", trainerList);

        player.generateStartingInventory(15);

        combat = new Combat(player, trainer);
    }
    public void switchPuckemon(int i){player.switchPuckemon(i);}

    public Puckemon getPlayerPuckemon() {
        return player.getPuckemon();
    }

    public Puckemon getTrainerPuckemon() {
        return trainer.getPuckemon();
    }

    public void useAttack(int index){
        combat.usePlayerAttack(index);
    }

    public void useItem(int index) {combat.usePlayerItem(index);}

    public void useSwitch(){
        combat.useSwitch();
    }

    public Attack getAttack(int index){
        return player.getPuckemon().getMoveSet().get(index);
    }

    public List<Puckemon> getParty(){
        return player.getParty();
    }

    public List<Item> getInventory(){ return player.getInventory();}
}