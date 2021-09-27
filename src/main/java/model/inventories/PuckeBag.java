package model.inventories;

import model.entities.Puckemon;
import java.util.ArrayList;
import java.util.Collections;

public class PuckeBag {

    private ArrayList<Puckemon> activeParty = new ArrayList<Puckemon>();
    private ArrayList<Puckemon> inactiveParty = new ArrayList<>();
    private Puckemon activePuckemon = activeParty.get(0);

    public PuckeBag(int id,int level){
        Puckemon puckemon = new Puckemon(id, level);
        addToActiveParty(puckemon);
    }


    private void switchPuckemon(int index){
        Collections.swap(activeParty,1, index);

    }

    private void addToActiveParty(Puckemon puckemon) {
        if (activeParty.size() >= 6){
            inactiveParty.add(puckemon);
        }else{
            activeParty.add(puckemon);
        }
    }







    public Puckemon getActivePuckemon(){
        return activePuckemon;
    }


}
