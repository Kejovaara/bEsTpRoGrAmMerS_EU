package model.inventories;

import model.entities.CreatePuckemon;
import model.entities.OwnedPuckemon;
import model.entities.Puckemon;

import java.util.ArrayList;
import java.util.List;

public class PlayerBag extends PuckeBag{

    private final List<OwnedPuckemon> box = new ArrayList<>();
    private final CreatePuckemon createPuckemon;

    public PlayerBag(List<OwnedPuckemon> puckemons) {
        super(puckemons);
        createPuckemon = new CreatePuckemon();
    }

    private void addToParty(OwnedPuckemon puckemon) {
        if (party.size() >= 6){
            box.add(puckemon);
        }else{
            party.add(puckemon);
        }
    }

    private void evolvePuckemon(){
        for (int i = 0; i < party.size(); i++) {
            OwnedPuckemon puckemon = (OwnedPuckemon) party.get(i);
            if(puckemon.getEvolve() && puckemon.getHealth() != 0){
                party.add(i,createPuckemon.createOwnedPuckemon(puckemon.getEvolutionId(),puckemon.getLevel()));
            }
        }
    }

    public List<OwnedPuckemon> getBox() {
        return box;
    }
    public void addToBox(OwnedPuckemon puckemon){
        box.add(puckemon);
    }
    public void addPuckemonToParty(OwnedPuckemon puckemon){addToParty(puckemon);}
    public void checkEvolution(){evolvePuckemon();};
}
