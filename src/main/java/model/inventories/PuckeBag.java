package model.inventories;

import model.entities.OwnedPuckemon;
import model.entities.Puckemon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PuckeBag {

    private final List<Puckemon> party = new ArrayList<>();
    private final List<Puckemon> box = new ArrayList<>();

    public PuckeBag(List<OwnedPuckemon> puckemons){
        for (int i = 0; i < puckemons.size(); i++) {
            if(party.size()<6){
                addToActiveParty(puckemons.get(i));
            }else{
                addToInactiveParty(puckemons.get(i));
            }
        }
    }

    private void addToInactiveParty(Puckemon puckemon) {
        box.add(puckemon);
    }


    private void switchPuckemon(int index){
        Collections.swap(party,0, index);

    }

    private void addToActiveParty(Puckemon puckemon) {
        if (party.size() >= 6){
            box.add(puckemon);
        }else{
            party.add(puckemon);
        }
    }


    public List<Puckemon> getParty(){ return party;}
    public void setActivePuckemon(int index) {
        switchPuckemon(index);
    }
    public Puckemon getActivePuckemon(){return party.get(0);}
    public void addPuckemonToParty(Puckemon puckemon){addToActiveParty(puckemon);}



}
