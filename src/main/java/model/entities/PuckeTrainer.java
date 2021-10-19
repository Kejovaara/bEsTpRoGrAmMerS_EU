package model.entities;

import model.PTypes;
import model.attack.Attack;
import model.effects.EffectHelper;
import model.effects.IEffectContainer;
import model.inventories.Inventory;
import model.inventories.Item;
import model.inventories.PuckeBag;
import view.message.MessageHandler;
import model.inventories.TrainerBag;

import java.util.List;
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

//    public Puckemon selectPuckemon(){
//        Puckemon puckemon = puckeBag.getNextPuckemon();
//        return puckemon;
//    }

    @Override
    public boolean checkIfDefeated(){
        boolean defeated = true;
        for (int i = 0; i < puckeBag.getParty().size(); i++) {
            if(puckeBag.getParty().get(i).currentHealth>0){
                defeated = false;
                break;
            }
        }
        return defeated;
    }

    public IEffectContainer makeMove(IPuckemon enemy) {
        int index = 0;
        Puckemon activePuckemon = puckeBag.getActivePuckemon();

        //If Puckemon fainted
        if (activePuckemon.getHealth() <= 0) {
            int partySize = puckeBag.getParty().size();

            //Switch to random puckemon which is alive
            for (int i = 0; i <= partySize; i++) {
                //If no puckemon is alive. Perhaps not useful after end game implementation?
                if (i == partySize) {
                    MessageHandler.getInstance().DisplayMessage("GAME WON");
                    //End game?
                    return null;
                }

                Random rand = new Random(); //instance of random class
                index = rand.nextInt(partySize);

                //Save index if puckemon has health
                if (puckeBag.getParty().get(index).getHealth() > 0) {
                    break;
                }
            }

            switchPuckemon(index);
            return null;
        }

        if (this.smart) {
            List<VildPuckemon> party = puckeBag.getParty();
            double bestMultiplier = 0;
            Puckemon bestPuckemon = activePuckemon;
            int bestPuckemonId = 0;

            //The threshold for when the difference between the best multiplier and active Puckemon multiplier is
            // high enough for switching Puckemon
            double threshold = 0.2;

            //Save best multiplier and index for active puckemon
            double bestMultiplierActiveP = 0;
            int indexActiveP = 0;

            //Check for the most effective puckemon
            for (int i = 0; i < party.size(); i++) {
                Puckemon puckemon = party.get(i);

                List<Attack> attacks = puckemon.getMoveSet();

                //Check for the (perhaps) most effective attack
                for (int j = 0; j < attacks.size(); j++) {
                    Attack attack = attacks.get(j);
                    double attackMultiplier = EffectHelper.getMultplier(attack.getType(), enemy.getTypes());

                    List<PTypes> types = puckemon.getTypes();

                    if(types.get(0) == attack.getType() || types.get(types.size()-1) == attack.getType()){
                        attackMultiplier *= 1.5;
                    }

                    if(puckemon.equals(activePuckemon)) {
                        if (attackMultiplier > bestMultiplierActiveP) {
                            bestMultiplierActiveP = attackMultiplier;
                            indexActiveP = i;
                        }
                    }

                    if (attackMultiplier > bestMultiplier) {
                        index = j;
                        bestMultiplier = attackMultiplier;
                        bestPuckemon = puckemon;
                        bestPuckemonId = i;
                    }
                }
            }

            double diff = bestMultiplier - bestMultiplierActiveP;

            System.out.println("Diff: " + diff);
            System.out.println("Multiplier: " + bestMultiplier);

            if (!(bestPuckemon.equals(activePuckemon))) {
//                double diff = bestMultiplier - bestMultiplierActiveP;

                //Only switch Puckemon if difference is high enough
                if (diff > threshold){
                    switchPuckemon(bestPuckemonId);
                    return null;
                }
                index = indexActiveP;
            }

        } else {
            Random rand = new Random(); //instance of random class
            int upperbound = activePuckemon.getMoveSet().size();
            //generate random values from 0-3
            index = rand.nextInt(upperbound);

            //TODO: Fix so that it gets random attack
        }

        MessageHandler.getInstance().DisplayMessage("Opponent " + puckeBag.getActivePuckemon().getName() + " attacked!");
        return activePuckemon.getAttack(index);
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
