package model;

import model.attack.Attack;
import model.attack.AttackFactory;
import model.combat.Combat;
import model.entities.*;
import model.inventories.Item;
import model.inventories.ItemFactory;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private OwnedPuckemon playerPuckemon = new OwnedPuckemon(1, 1);
    private OwnedPuckemon playerPuckemon1 = new OwnedPuckemon(1, 2);
    private OwnedPuckemon playerPuckemon2 = new OwnedPuckemon(50, 3);
    private OwnedPuckemon playerPuckemon3 = new OwnedPuckemon(10, 128);

    private OwnedPuckemon trainerPuckemon = new OwnedPuckemon(10, 3);
    private List<OwnedPuckemon> playerList = new ArrayList<>();
    private List<OwnedPuckemon> trainerList = new ArrayList<>();


    private Player player;
    private PuckeTrainer trainer;
    private Combat combat;
    private GameBuilder gameBuilder;

    public Model() {
        gameBuilder = new GameBuilder();
        player = new Player(gameBuilder.getPlayerStartingTeam(), 10);
        trainer = new PuckeTrainer("Bertil the great", gameBuilder.getRandOpponentTeam(5,5), true);

        player.generateStartingInventoryDEV(35);

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
        if (getAttack(index).getPP() > 0)combat.usePlayerAttack(index);
    }

    public void useItem(int index) {combat.usePlayerItem(index);}

    public void useSwitch(){
        combat.useSwitch();
    }

    public Attack getAttack(int index){
        return player.getPuckemon().getMoveSet().get(index);
    }
    public List<Attack> getAttacks(){
        return player.getPuckemon().getMoveSet();
    }

    public List<Puckemon> getParty(){
        return player.getParty();
    }

    public List<Item> getInventory(){ return player.getInventory();}
}