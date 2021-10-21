package model.inventories;

import model.entities.CreatePuckemon;
import model.entities.OwnedPuckemon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Holds the Players puckemon, the puckemon they are currently not using and alters or gives the Players puckemon.
 * <br> Party holds 1-6 puckemon and are the ones the player uses in battle.
 * <br> Box holds all the puckemon that the player is not currently using.
 * @author Lukas Jigberg
 */
public class PlayerBag extends PuckeBag{

    private final List<OwnedPuckemon> box = new ArrayList<>();
    private final CreatePuckemon createPuckemon;
    private final List<OwnedPuckemon> party = new ArrayList<>();

    public PlayerBag(List<OwnedPuckemon> puckemons) {
        for (OwnedPuckemon puckemon : puckemons) {
            addToParty(puckemon);
        }
        createPuckemon = new CreatePuckemon();
    }

    private void addToParty(OwnedPuckemon puckemon) {
        if (party.size() >= 6){
            box.add(puckemon);
        }else{
            party.add(puckemon);
        }
    }

    /**
     * @param index The index of the Puckemon the player want to switch to.
     */
    private void switchPuckemon(int index){
        Collections.swap(party,0, index);
    }

    /**
     * Gives each Puckemon in the party a set amount of experience point
     */
    private void giveOutExp(){
        for (OwnedPuckemon ownedPuckemon : party) {
            if (ownedPuckemon.getHealth() > 0) {
                ownedPuckemon.giveExp(1000);
            }
        }
    }

    /**
     * If a puckemon is ready to evolve and has hp remaining it will be replaced by its evolution. It will keep its specific level and nickname.
     */
    private void evolvePuckemon(){
        for (int i = 0; i < party.size(); i++) {
            if(party.get(i).getEvolve() && party.get(i).getHealth() != 0){
                party.set(i,createPuckemon.createOwnedPuckemon(party.get(i).getEvolutionId(),party.get(i).getLevel()));
            }
        }
    }

//    public List<OwnedPuckemon> getBox() {
//        return box;
//    }
//    public void addToBox(OwnedPuckemon puckemon){
//        box.add(puckemon);
//    }
//    public void addPuckemonToParty(OwnedPuckemon puckemon){
//    addToParty(puckemon);
//}
    public List<OwnedPuckemon> getParty(){
        return party;}
    public void setActivePuckemon(int index) {
        switchPuckemon(index);
    }
    public OwnedPuckemon getActivePuckemon(){return party.get(0);}

    public void giveVictoryRewards(){
        giveOutExp();
        evolvePuckemon();
    }
}
