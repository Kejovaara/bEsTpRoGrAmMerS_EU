package model.entities;

import model.PTypes;
import model.attack.Attack;
import model.effects.EffectHelper;
import model.effects.IEffectContainer;
import model.entities.puckemon.Puckemon;
import model.entities.puckemon.FixedPuckemon;
import model.inventories.Inventory;
import model.inventories.Item;
import org.apache.commons.math3.util.Pair;
import services.observers.MessageHandler;
import model.inventories.TrainerPuckeBag;

import java.util.List;
import java.util.Random;

/**
 * A class representing a Puckemon trainer meant to function as an opponent in the game similar to the player.
 * @author Emil Jonsson
 */
public class PuckeTrainer implements IFighter, ITrainer {
    private String name;
    private final TrainerPuckeBag trainerBag;
    private final Inventory inventory;
    private final boolean smart;

    /**
     * First constructor of PuckeTrainer which allows Items to be added to the inventory
     * @param name the name of the PuckeTrainer
     * @param puckemons list of Puckemons to add to the trainers trainerBag
     * @param inventory a class containing items
     * @param smart boolean to specify if PuckeTrainer should be able to make smart/calculated moves. False if random
     *              available attack should be returned from makeMove method.
     */
    public PuckeTrainer(String name, List<FixedPuckemon> puckemons, Inventory inventory, boolean smart){
        this.name = name;
        this.trainerBag = new TrainerPuckeBag(puckemons);
        this.inventory = inventory;
        this.smart = smart;
    }

    /**
     * Second constructor of PuckeTrainer which does not allow Items to be added to the inventory
     * @param name the name of the PuckeTrainer
     * @param puckemons list of Puckemons to add to the trainers trainerBag
     * @param smart boolean to specify if PuckeTrainer should be able to make smart/calculated moves. False if random
     *              available attack should be returned from makeMove method.
     */
    public PuckeTrainer(String name, List<FixedPuckemon> puckemons, boolean smart){
        this.name = name;
        this.trainerBag = new TrainerPuckeBag(puckemons);
        this.inventory = new Inventory();
        this.smart = smart;
    }

    /**
     * First checks if the active Puckemon fighting is defeated and tries to switch to one that is alive in the
     * trainerBag. If there is no Puckemon available the method returns true, representing that the trainer has been
     * defeated.
     * @return true if the trainer is defeated, false if there is Puckemon left to fight.
     */
    @Override
    public boolean checkIfDefeated(){
        Puckemon activePuckemon = trainerBag.getActivePuckemon();
        List<FixedPuckemon> party = trainerBag.getParty();

        //If active Puckemon fainted
        if (activePuckemon.getHealth() <= 0) {
            int partySize = party.size();
            int index = 0;


            //Switch to puckemon which is alive
            for (int i = 0; i <= partySize; i++) {
                index = i;
                //If no puckemon is alive, return defeated = true
                if (i == partySize) {
                    return true;
                }

                //Save index if puckemon has health
                if (party.get(i).getHealth() > 0) {
                    break;
                }
            }
            MessageHandler.getInstance().DisplayMessage("Opponent " + activePuckemon.getName()
                    + " fainted and switched to " + party.get(index).getName() + "!");
            switchPuckemon(index);
        }

        //If at least one Puckemon is alive
        return false;
    }

    /**
     * Makes a move during combat and returns the move if one is made. This implementation is "smart" if variable "smart"
     * is true. This means that the move returned is supposed to be calculated and not random. The return value can
     * therefore be null if the chosen move is to simply switch the active Puckemon, in which case no effect should be
     * returned.
     * If the variable "smart" is false a random available attack is returned.
     * @param enemyP the opposing Puckemon, meant to be used to make calculated moves
     * @return an IEffectContainer containing effects from the move chosen, if no move chosen returns null!
     */
    @Override
    public IEffectContainer makeMove(IPuckemon enemyP) {
        int attackIndex;
        Puckemon activePuckemon = trainerBag.getActivePuckemon();

        if (this.smart) {
            //Check for the most effective puckemon and its index
            Pair<Integer, Integer> bestPuckemonPair = mostEffectivePuckemon(enemyP);
            attackIndex = bestPuckemonPair.getFirst();
            int bestPuckemonIndex = bestPuckemonPair.getSecond();

            //If bestPuckemon does not have index 0 it is not the active puckemon
            if (bestPuckemonIndex != 0) {
                switchPuckemon(bestPuckemonIndex);
                MessageHandler.getInstance().DisplayMessage("Opponent switched to " + trainerBag.getActivePuckemon().getName()
                        + "!");
                return null;
            }

        } else {
            //Return random attack
            Random rand = new Random(); //instance of random class
            int upperbound = activePuckemon.getMoveSet().size();
            attackIndex = rand.nextInt(upperbound);

        }

        MessageHandler.getInstance().DisplayMessage("Opponent " + trainerBag.getActivePuckemon().getName() + " used "+activePuckemon.getAttack(attackIndex).getName());
        return activePuckemon.getAttack(attackIndex);
    }

    /**
     * Returns the most effective Puckemon from the TrainerBag based on which one has the most effective attack against an
     * enemy Puckemon
     * @param enemyP the opposing Puckemon
     * @return a pair of Integers where the first one is the index for the most effective attack on the most effective
     *          Puckemon. The second Integer is the index for the most effective Puckemon in the TrainerBag
     */
    private Pair<Integer, Integer> mostEffectivePuckemon(IPuckemon enemyP) {
        List<FixedPuckemon> party = trainerBag.getParty();
        int index = 0;
        int puckemonIndex = 0;
        double bestMultiplier = 0;

        //Check for the most effective puckemon
        for (int i = 0; i < party.size(); i++) {
            Puckemon puckemon = party.get(i);

            //Check for the (perhaps) most effective attack
            Pair<Integer, Double> bestAttack = mostEffectiveAttack(puckemon, enemyP);
            int attackIndex = bestAttack.getFirst();
            double attackMultiplier = bestAttack.getSecond();

            //The threshold for when the difference between the best multiplier and active Puckemon multiplier is
            //high enough for switching Puckemon
            double threshold = 0.2;

            //Since the active puckemon is always at index 0, the diff variable is used to see if a multiplier is
            //high enough (over the threshold) for a switch to be worth it. Since the first bestMultiplier always will
            // be from the active Puckemon, the switch later will never occur if the diff it is not over the threshold!
            double diff = attackMultiplier - bestMultiplier;

            if (diff > threshold) {
                index = attackIndex;
                bestMultiplier = attackMultiplier;
                puckemonIndex = i;
            }
        }
        return new Pair<>(index, puckemonIndex);
    }

    /**
     * Returns the most effective attack from a Puckemon based on which one has the most effective attack against an
     * enemy Puckemon. This is based on a multiplier which in turn is based on the attack-type and the enemy Puckemons
     * types.
     * @param puckemon the Puckemon whose attacks are to be evaluated
     * @param enemyP the opposing Puckemon
     * @return a pair of Integer and Double where the Integer is the index for the most effective attack and the Double
     *          is the Multiplier for the most effective attack.
     */
    private Pair<Integer, Double> mostEffectiveAttack(Puckemon puckemon, IPuckemon enemyP) {
        List<Attack> attacks = puckemon.getMoveSet();
        int index = 0;
        double bestMultiplier = 0;

        //Get the most effective attack based on type
        for (int j = 0; j < attacks.size(); j++) {
            Attack attack = attacks.get(j);

            //Calculate the multiplier based on the attack-type and enemy's types
            double attackMultiplier = EffectHelper.getMultplier(attack.getType(), enemyP.getTypes());

            List<PTypes> types = puckemon.getTypes();

            //Checks if the Puckemon has one type that is the same as the current attack, which boosts the multiplier.
            if(types.get(0) == attack.getType() || types.get(types.size()-1) == attack.getType()){
                attackMultiplier *= 1.5;
            }

            if (attackMultiplier > bestMultiplier) {
                index = j;
                bestMultiplier = attackMultiplier;
            }
        }
        return new Pair<>(index, bestMultiplier);
    }

    /**
     * Returns the Puckemon currently fighting
     * @return active puckemon as an IPuckemon
     */
    @Override
    public IPuckemon getActivePuckemon() {
        return trainerBag.getActivePuckemon();
    }

    /**
     * Switches Puckemon to the specified index in the TrainerBag.
     * @param index the index where the Puckemon lies in the TrainerBag
     */
    @Override
    public void switchPuckemon(int index){
        trainerBag.switchPuckemon(index);
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
     * Adds item to the Inventory
     * @param item the item to be added to the Inventory
     */
    public void addItem(Item item){ inventory.addItem(item);}

    public void useItem(Item item){
        // item.execute();
    }
}
