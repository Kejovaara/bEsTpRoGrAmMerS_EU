package model.inventories;

import model.entities.puckemon.FixedPuckemon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrainerPuckeBag {

    private final List<FixedPuckemon> party = new ArrayList<>();

    public TrainerPuckeBag(List<FixedPuckemon> puckemons){
        for (int i = 0; i < puckemons.size(); i++) {
            addToParty(puckemons.get(i));
        }
    }

    private void addToParty(FixedPuckemon puckemon) {
        if (party.size() < 6){
            party.add(puckemon);
        }
    }

    public void switchPuckemon(int index){
        Collections.swap(party,0, index);
        party.get(0).resetStats();
    }

    public List<FixedPuckemon> getParty(){
        return party;
    }
    public FixedPuckemon getActivePuckemon(){
        return party.get(0);
    }

}
