package model.combat;

import model.entities.IFighter;

import java.util.ArrayList;
import java.util.List;

public class Combat {

    private List<IFighter> players = new ArrayList<IFighter>();

    IFighter player;
    IFighter opponent;

    public Combat(IFighter player, IFighter opponent){
        this.player = player;
        this.opponent = opponent;
        enterCombat(this.player, this.opponent);
    }

    public void enterCombat(IFighter player, IFighter opponent){
        players.add(this.player);
        players.add(this.opponent);
    }

    public boolean isCombat(){
        //TODO
        while(1 == 1 /*this.player.getHealth() > 0 || this.opponent.getHealth()*/ ){
            return true;
        }
    }

    public void removeFromCombat(IFighter player, IFighter opponent){
        if(!isCombat()){
            players.remove(player);
            players.remove(opponent);
        }
    }

}
