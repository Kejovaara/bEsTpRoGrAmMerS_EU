package model.inventories;

import model.entities.CreatePuckemon;
import model.entities.OwnedPuckemon;
import model.entities.Puckemon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class PuckeBag<T extends Puckemon> {

    protected final List<T> party = new ArrayList<>();

    public PuckeBag(List<T> puckemons){
        for (int i = 0; i < puckemons.size(); i++) {
            if(party.size()<6){
                party.add(puckemons.get(i));
            }else{
                break;
            }
        }
    }

    protected void switchPuckemon(int index){
        Collections.swap(party,0, index);
    }

    protected void addToParty(Puckemon puckemon) {
        if (party.size() >= 6){
            System.out.println("Party is currently full");
        }else{
            party.add((T)puckemon);
        }
    }


    public List<Puckemon> getParty(){ return (List<Puckemon>) party;}
    public void setActivePuckemon(int index) {
        switchPuckemon(index);
    }
    public Puckemon getActivePuckemon(){return party.get(0);}
    public void addPuckemonToParty(Puckemon puckemon){addToParty(puckemon);}



}
