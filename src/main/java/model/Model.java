package model;

import model.attack.Attack;
import model.combat.Combat;
import model.entities.*;
import model.inventories.Item;

import java.util.List;

public class Model {

    private Player player;
    private PuckeTrainer trainer;
    private Combat combat;
    private PartyBuilder partyBuilder;

    public Model() {
        partyBuilder = new PartyBuilder();
        player = new Player(partyBuilder.getPlayerStartingTeam(), 10);
        player.generateStartingInventoryDEV(35);
        startCombat(3,10,false);
    }

    public void startCombat(int size, int minLevel, boolean smart){
        createNoviceTrainer(size, minLevel, smart);
        combat = new Combat(player, trainer);
    }

    private PuckeTrainer createNoviceTrainer(int size, int minLevel, boolean smart){
        trainer = new PuckeTrainer("Bertil", partyBuilder.getRandOpponentTeam(size,minLevel), smart);
        return trainer;
    }

    public void switchPuckemon(int i){player.switchPuckemon(i);}

    public Puckemon getPlayerPuckemon() {
        return player.getPuckemon();
    }

    public IPuckemon getTrainerPuckemon() {
        return trainer.getActivePuckemon();
    }

    public void useAttack(int index){
        if (getAttack(index).getPP() > 0)combat.usePlayerAttack(index);
    }

    public void useItem(int index) {combat.usePlayerItem(index);}

    public void useSwitch(){
        combat.useSwitch();
    }

    public void useFlee(){
        combat.useFlee();
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
    public String getBattleOutcome(){return combat.getBattleOutcome();}
}