package model.entities;

import model.PTypes;
import model.attack.Attack;
import model.effects.EffectHelper;
import model.effects.IEffectContainer;
import model.inventories.Inventory;
import model.inventories.Item;
import model.inventories.PuckeBag;

import java.util.List;
import java.util.Random;

public class PuckeTrainer implements IFighter, ITrainer {
    private String name;
    private PuckeBag puckeBag;
    private Inventory inventory;
    private boolean smart;

    public PuckeTrainer(String name, List<OwnedPuckemon> puckemons, List<Item> items, boolean smart){
        this.name = name;
        this.puckeBag = new PuckeBag(puckemons);
        this.inventory = new Inventory(items);
        this.smart = smart;
    }

    public PuckeTrainer(String name, List<OwnedPuckemon> puckemons, boolean smart){
        this.name = name;
        this.puckeBag = new PuckeBag(puckemons);
        this.inventory = new Inventory();
        this.smart = smart;
    }

//    public Puckemon selectPuckemon(){
//        Puckemon puckemon = puckeBag.getNextPuckemon();
//        return puckemon;
//    }

    public IEffectContainer makeMove(IPuckemon enemy) {
        int index = 0;
        Puckemon activePuckemon = puckeBag.getActivePuckemon();

        if (this.smart) {
            List<Puckemon> party = puckeBag.getParty();
            double bestMultiplier = 0;
            Puckemon bestPuckemon = activePuckemon;
            int bestPuckemonId = 0;

            //Save best multiplier and index for active puckemon
            double bestMultiplierActiveP = 0;
            int indexActiveP;

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

                System.out.println("Diff: " + diff);

                //Only switch Puckemon if difference is high enough
                if (diff > 0.2){
                    switchPuckemon(bestPuckemonId);
                }
            }

        } else {
            Random rand = new Random(); //instance of random class
            int upperbound = activePuckemon.getMoveSet().size();
            System.out.println("get upperbound:" + upperbound);
            //generate random values from 0-3
            index = rand.nextInt(upperbound);


            //TODO: Fix so that it gets random attack
        }

        index = 0;
        System.out.println("get attack:" + index);
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
    }

    public IEffectContainer getItem(int index) {
        return inventory.getItem(index);
    }

    public void addItem(Item item){ inventory.addItem(item);}

    public void useItem(Item item){
        // item.execute();
    }
}
