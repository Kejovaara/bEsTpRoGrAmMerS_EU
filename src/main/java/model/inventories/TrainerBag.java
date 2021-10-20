package model.inventories;

import model.entities.VildPuckemon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrainerBag extends PuckeBag{

    private final List<VildPuckemon> party = new ArrayList<>();

    public TrainerBag(List<VildPuckemon> puckemons){
        for (int i = 0; i < puckemons.size(); i++) {
            addToParty(puckemons.get(i));
        }
    }

    private void addToParty(VildPuckemon puckemon) {
        if (party.size() < 6){
            party.add(puckemon);
        }
    }

    protected void switchPuckemon(int index){
        Collections.swap(party,0, index);
    }

    public List<VildPuckemon> getParty(){
        return party;
    }
    public void setActivePuckemon(int index) {
        switchPuckemon(index);
    }
    public VildPuckemon getActivePuckemon(){
        return party.get(0);
    }
    public void addPuckemonToParty(VildPuckemon puckemon){addToParty(puckemon);}

}
