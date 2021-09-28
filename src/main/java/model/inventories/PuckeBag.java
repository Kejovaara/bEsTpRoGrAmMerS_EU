package model.inventories;

import model.entities.OwnedPuckemon;
import java.util.ArrayList;
import java.util.Collections;

public class PuckeBag {

    private ArrayList<OwnedPuckemon> activeParty = new ArrayList<OwnedPuckemon>();
    private ArrayList<OwnedPuckemon> inactiveParty = new ArrayList<>();
//    private OwnedPuckemon activePuckemon = activeParty.get(0);

    public PuckeBag(int id,int level){
        OwnedPuckemon puckemon = new OwnedPuckemon(id, level);
        addToActiveParty(puckemon);
    }


    private void switchPuckemon(int index){
        Collections.swap(activeParty,1, index);

    }

    private void addToActiveParty(OwnedPuckemon puckemon) {
        if (activeParty.size() >= 6){
            inactiveParty.add(puckemon);
        }else{
            activeParty.add(puckemon);
        }
    }




//    public OwnedPuckemon getActivePuckemon(){
//        return activePuckemon;
//    }
    public void setActiveParty(int index) {
        switchPuckemon(index);
    }



}
