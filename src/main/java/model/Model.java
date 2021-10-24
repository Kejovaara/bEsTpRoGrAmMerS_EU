package model;

import model.attack.Attack;
import model.combat.Combat;
import model.entities.*;
import model.puckemon.IPuckemon;
import model.puckemon.OwnedPuckemon;
import model.puckemon.Puckemon;
import model.inventories.Item;
import services.observers.MessageHandler;

import java.util.List;

/**
 * This class represents the actual model of the game, contains methods that the game uses to operate.
 * @author Rasmus Almryd
 * @author Lukas Jigberg
 * @author André Kejovaara
 * @author Emil Jonsson
 */
public class Model {

    private final Player player;
    private IFighter opponent;
    private Combat combat;
    private final PartyBuilder partyBuilder;
    private boolean fleePermitted = true;

    /**
     * Constructor of the model, generates what's needed for playing the game.
     */
    public Model() {
        partyBuilder = new PartyBuilder();
        player = new Player(partyBuilder.getPlayerStartingTeam(), 10);
        player.generateStartingInventory(35);
        startCombatTrainer(1,10,false);
    }

    /**
     * Method for starting combat with a trainer
     * @param size is the size of party
     * @param minLevel minimum level of the puckemons that is generated
     * @param smart decides the difficulty of the trainer
     */
    public void startCombatTrainer(int size, int minLevel, boolean smart){
        fleePermitted = false;
        createNoviceTrainer(size, minLevel, smart);
        combat = new Combat(player, opponent);
    }

    /**
     * Method for starting combat with a random wild Puckemon
     * @param minLevel minimum level of the puckemon that is generated
     */
    public void startCombatWildPuckemon(int minLevel){
        fleePermitted = true;
        opponent = partyBuilder.createRandomWildPuckemon(minLevel);
        combat = new Combat(player, opponent);
    }

    private void createNoviceTrainer(int size, int minLevel, boolean smart){
        opponent = new PuckeTrainer("Bertil", partyBuilder.getRandOpponentTeam(size,minLevel), smart);
    }

    /**
     * Switches puckemon in the bag.
     * @param i index of the puckemon to be switched to.
     */
    public void switchPuckemon(int i){player.switchPuckemon(i);}

    /**
     * @return the players puckemon.
     */
    public Puckemon getActivePlayerPuckemon() {
        return player.getActivePuckemon();
    }

    public Puckemon getPlayerPuckemon(int index) {
        return player.getParty().get(index);
    }

    /**
     * @return the opponents active puckemon.
     */
    public IPuckemon getOpponentPuckemon() {
        return opponent.getActivePuckemon();
    }

    /**
     * Uses an attack of the given index
     * @param index the given index which decides what attack to use
     */
    public void useAttack(int index){
        if (getAttack(index).getPP() > 0)combat.usePlayerAttack(index);
    }

    /**
     * Use the chosen item in the inventory
     * @param index the index of the item in the inventory
     */
    public void useItem(int index) {combat.usePlayerItem(index);}

    /**
     * Use switch method
     */
    public void useSwitch(){
        combat.useSwitch();
    }

    /**
     * Use flee method
     */
    public void useFlee(){
        if (fleePermitted){
            combat.useFlee();
        } else {
            MessageHandler.getInstance().DisplayMessage("You cannot flee when fighting a trainer!");
        }
    }

    /**
     * @param index of the given attack
     * @return the chosen attack.
     */
    public Attack getAttack(int index){
        return player.getActivePuckemon().getMoveSet().get(index);
    }

    /**
     * @return the list of attacks a puckemon has.
     */
    public List<Attack> getAttacks(){
        return player.getActivePuckemon().getMoveSet();
    }

    /**
     * @return the party of the player
     */
    public List<OwnedPuckemon> getParty(){
        return player.getParty();
    }

    /**
     * @return the list of items in the players inventory.
     */
    public List<Item> getInventory(){ return player.getInventory();}

    /**
     * @return the outcome of the battle
     */
    public String getBattleOutcome(){return combat.getBattleOutcome();}
}