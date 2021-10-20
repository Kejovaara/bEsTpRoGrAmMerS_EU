package model.entities;

import model.PTypes;
import model.attack.Attack;
import model.effects.EffectHelper;
import model.effects.IEffectContainer;
import model.inventories.Inventory;
import model.inventories.Item;
import model.inventories.PuckeBag;
import org.apache.commons.math3.util.Pair;
import view.message.MessageHandler;
import model.inventories.TrainerBag;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class PuckeTrainer implements IFighter, ITrainer {
    private String name;
    private TrainerBag puckeBag;
    private Inventory inventory;
    private boolean smart;

    public PuckeTrainer(String name, List<VildPuckemon> puckemons, List<Item> items, boolean smart){
        this.name = name;
        this.puckeBag = new TrainerBag(puckemons);
        this.inventory = new Inventory(items);
        this.smart = smart;
    }

    public PuckeTrainer(String name, List<VildPuckemon> puckemons, boolean smart){
        this.name = name;
        this.puckeBag = new TrainerBag(puckemons);
        this.inventory = new Inventory();
        this.smart = smart;
    }

    @Override
    public boolean checkIfDefeated(){
        Puckemon activePuckemon = puckeBag.getActivePuckemon();
        List<VildPuckemon> party = puckeBag.getParty();

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

            switchPuckemon(index);
        }

        //If at least one Puckemon is alive
        return false;
    }

    public IEffectContainer makeMove(IPuckemon enemy) {
        int attackIndex = 0;
        Puckemon activePuckemon = puckeBag.getActivePuckemon();

        if (this.smart) {
            //Check for the most effective puckemon and its multiplier
            Pair<Integer, Integer> bestPuckemonPair = mostEffectivePuckemon(enemy);
            attackIndex = bestPuckemonPair.getFirst();
            int bestPuckemonIndex = bestPuckemonPair.getSecond();

            //If bestPuckemon does not have index 0 it is not the active puckemon
            if (bestPuckemonIndex != 0) {
                switchPuckemon(bestPuckemonIndex);
                return null;
            }

        } else {
            //Return random attack
            Random rand = new Random(); //instance of random class
            int upperbound = activePuckemon.getMoveSet().size();
            //generate random values from 0-3
            attackIndex = rand.nextInt(upperbound);
        }

        System.out.println("Index: " + attackIndex);

        MessageHandler.getInstance().DisplayMessage("Opponent " + puckeBag.getActivePuckemon().getName() + " attacked!");
        return activePuckemon.getAttack(attackIndex);
    }

    private Pair<Integer, Integer> mostEffectivePuckemon(IPuckemon enemy) {
        List<VildPuckemon> party = puckeBag.getParty();
        int index = 0;
        int puckemonIndex = 0;
        double bestMultiplier = 0;

        //Check for the most effective puckemon
        for (int i = 0; i < party.size(); i++) {
            Puckemon puckemon = party.get(i);

            //Check for the (perhaps) most effective attack
            Pair<Integer, Double> bestAttack = mostEffectiveAttack(puckemon, enemy);
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

    private Pair<Integer, Double> mostEffectiveAttack(Puckemon puckemon, IPuckemon enemy) {
        List<Attack> attacks = puckemon.getMoveSet();
        int index = 0;
        double bestMultiplier = 0;

        //Get the most effective attack based on type
        for (int j = 0; j < attacks.size(); j++) {
            Attack attack = attacks.get(j);
            double attackMultiplier = EffectHelper.getMultplier(attack.getType(), enemy.getTypes());

            List<PTypes> types = puckemon.getTypes();

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

    @Override
    public IPuckemon getActivePuckemon() {
        return puckeBag.getActivePuckemon();
    }

    public Puckemon getPuckemon(){
        return puckeBag.getActivePuckemon();
    }

    // Pick target in party to switch too
    public void switchPuckemon(int index){
        puckeBag.setActivePuckemon(index);
        MessageHandler.getInstance().DisplayMessage("Opponent switched to " + puckeBag.getActivePuckemon().getName()
                + "!");
    }

    public IEffectContainer getItem(int index) {
        return inventory.getItem(index);
    }

    public void addItem(Item item){ inventory.addItem(item);}

    public void useItem(Item item){
        // item.execute();
    }
}
