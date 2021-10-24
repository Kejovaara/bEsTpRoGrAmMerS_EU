package model.puckeBags;

import model.puckemon.FixedPuckemon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The opponent trainers bag.
 * @author Lukas Jigberg
 */
public class TrainerPuckeBag {

    private final List<FixedPuckemon> party = new ArrayList<>();

    /**
     * Constructor.
     * @param puckemons List of FixedPuckemons to add to bag.
     */
    public TrainerPuckeBag(List<FixedPuckemon> puckemons){
        for (FixedPuckemon puckemon : puckemons) {
            addToParty(puckemon);
        }
    }

    private void addToParty(FixedPuckemon puckemon) {
        if (party.size() < 6){
            party.add(puckemon);
        }
    }

    /**
     * Switch active puckemon in battle.
     * @param index index of puckemon in bag to switch to.
     */
    public void switchPuckemon(int index){
        Collections.swap(party,0, index);
        party.get(0).resetStats();
    }

    /**
     * Gets the party contained in the bag.
     * @return List of FixedPuckemon.
     */
    public List<FixedPuckemon> getParty(){
        return party;
    }

    /**
     * Gets the currently fighting Puckemon.
     * @return the active FixedPuckemon.
     */
    public FixedPuckemon getActivePuckemon(){
        return party.get(0);
    }

}
