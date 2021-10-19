package model;

import model.attack.Attack;
import model.combat.Combat;
import model.entities.*;
import model.inventories.Item;
import model.inventories.ItemFactory;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private Player player;
    private PuckeTrainer trainer;
    private Combat combat;
    private GameBuilder gameBuilder;

    public Model() {
        gameBuilder = new GameBuilder();
        player = new Player(gameBuilder.getPlayerStartingTeam(), 10);
        trainer = new PuckeTrainer("Bertil the great", gameBuilder.getRandOpponentTeam(1,5), false);

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

    public List<OwnedPuckemon> getParty(){
        return player.getParty();
    }

    public List<Item> getInventory(){ return player.getInventory();}
}