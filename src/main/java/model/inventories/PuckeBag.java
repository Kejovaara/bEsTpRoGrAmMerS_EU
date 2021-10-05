package model.inventories;

import model.entities.OwnedPuckemon;
import model.entities.Puckemon;

import java.util.ArrayList;
import java.util.Collections;

public class PuckeBag {

    private ArrayList<Puckemon> party = new ArrayList<Puckemon>();
    private ArrayList<Puckemon> box = new ArrayList<Puckemon>();

    public PuckeBag(int id,int level){
        OwnedPuckemon puckemon = new OwnedPuckemon(id, level);
        addToActiveParty(puckemon);
    }

    public PuckeBag(ArrayList<Puckemon> puckemons){
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
        Collections.swap(party,1, index);

    }

    private void addToActiveParty(Puckemon puckemon) {
        if (party.size() >= 6){
            box.add(puckemon);
        }else{
            party.add(puckemon);
        }
    }


    public void setActivePuckemon(int index) {
        switchPuckemon(index);
    }
    public Puckemon getActivePuckemon(){return party.get(0);}
    public void addPuckemonToParty(Puckemon puckemon){addToActiveParty(puckemon);}
    public void addPuckemonToBox(Puckemon puckemon){addToActiveParty(puckemon);}



}
