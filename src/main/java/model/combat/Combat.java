package model.combat;

import model.entities.ITrainer;

import java.util.ArrayList;
import java.util.List;

public class Combat {

    private List<ITrainer> players = new ArrayList<ITrainer>();

    ITrainer player;
    ITrainer opponent;

    public Combat(ITrainer player, ITrainer opponent){
        this.player = player;
        this.opponent = opponent;
        enterCombat(this.player, this.opponent);
    }

    public void enterCombat(ITrainer player, ITrainer opponent){
        players.add(this.player);
        players.add(this.opponent);
    }

    public boolean isCombat(){
        //TODO
        while(1 == 1 /*this.player.getHealth() > 0 || this.opponent.getHealth()*/ ){
            return true;
        }
    }

    public void removeFromCombat(ITrainer player, ITrainer opponent){
        if(!isCombat()){
            players.remove(player);
            players.remove(opponent);
        }
    }

}
