package model.inventories;

import model.entities.CreatePuckemon;
import model.entities.OwnedPuckemon;
import model.entities.Puckemon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerBag extends PuckeBag{

    private final List<OwnedPuckemon> box = new ArrayList<>();
    private final CreatePuckemon createPuckemon;
    private final List<OwnedPuckemon> party = new ArrayList<>();

    public PlayerBag(List<OwnedPuckemon> puckemons) {
        for (int i = 0; i < puckemons.size(); i++) {
            addToParty(puckemons.get(i));
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

    protected void switchPuckemon(int index){
        Collections.swap(party,0, index);
    }

    private void giveOutExp(){
        for (int i = 0; i < party.size(); i++) {
            party.get(i).giveExp(5000);
        }
    }

    private void evolvePuckemon(){
        for (int i = 0; i < party.size(); i++) {
            if(party.get(i).getEvolve() && party.get(i).getHealth() != 0){
                party.set(i,createPuckemon.createOwnedPuckemon(party.get(i).getEvolutionId(),party.get(i).getLevel()));
            }
        }
    }

    public List<OwnedPuckemon> getBox() {
        return box;
    }
    public void addToBox(OwnedPuckemon puckemon){
        box.add(puckemon);
    }
    public List<OwnedPuckemon> getParty(){
        return party;}
    public void setActivePuckemon(int index) {
        switchPuckemon(index);
    }
    public OwnedPuckemon getActivePuckemon(){return party.get(0);}
    public void addPuckemonToParty(OwnedPuckemon puckemon){
        addToParty(puckemon);
    }
    public void giveVictoryRewards(){
        giveOutExp();
        evolvePuckemon();
    }
}
