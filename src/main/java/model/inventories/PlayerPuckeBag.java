package model.inventories;

import services.puckemonGenerator.CreatePuckemon;
import model.entities.puckemon.OwnedPuckemon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Holds the Players puckemon, the puckemon they are currently not using and alters or gives the Players puckemon.
 * <br> Party holds 1-6 puckemon and are the ones the player uses in battle.
 * <br> Box holds all the puckemon that the player is not currently using.
 * @author Lukas Jigberg
 */
public class PlayerPuckeBag {

    private final List<OwnedPuckemon> box = new ArrayList<>();
    private final CreatePuckemon createPuckemon;
    private final List<OwnedPuckemon> party = new ArrayList<>();


    /**
     * @param puckemons List of OwnedPuckemon to add to bag.
     */
    public PlayerPuckeBag(List<OwnedPuckemon> puckemons) {
        for (OwnedPuckemon puckemon : puckemons) {
            addToParty(puckemon);
        }
        createPuckemon = new CreatePuckemon();
    }

    /**
     * Adds Puckemon to party. Adds it to the box if party is full.
     */
    private void addToParty(OwnedPuckemon puckemon) {
        if (party.size() >= 6) {
            box.add(puckemon);
        } else {
            party.add(puckemon);
        }
    }

    /**
     * Gives each Puckemon in the party a set amount of experience point
     */
    private void giveOutExp() {
        for (OwnedPuckemon ownedPuckemon : party) {
            if (ownedPuckemon.getHealth() > 0) {
                ownedPuckemon.giveExp(1000);
            }
        }
    }

    /**
     * If a puckemon is ready to evolve and has hp remaining it will be replaced by its evolution. It will keep its specific level and nickname.
     */
    private void evolvePuckemon() {
        for (int i = 0; i < party.size(); i++) {
            if (party.get(i).getEvolve() && party.get(i).getHealth() != 0) {
                party.set(i, createPuckemon.createOwnedPuckemon(party.get(i).getEvolutionId(), party.get(i).getLevel()));
            }
        }
    }

    /**
     * Choose index in party to switch to. Chosen Puckemon is "cleansed" of prior stats altercations to buff.
     */
    public void switchPuckemon(int index) {
        Collections.swap(party, 0, index);
        party.get(0).resetStats();
    }

    public void afterVictory() {
        giveOutExp();
        evolvePuckemon();
        for (OwnedPuckemon puckemon : party) {
            puckemon.resetStats();
        }
    }

    public List<OwnedPuckemon> getBox() {
        return box;
    }
    
    public List<OwnedPuckemon> getParty() {
        return party;
    }
    public OwnedPuckemon getActivePuckemon() {
        return party.get(0);
    }
}
