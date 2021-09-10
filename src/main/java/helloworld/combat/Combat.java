package helloworld.combat;

import java.util.ArrayList;
import java.util.List;

public class Combat implements ICombat {

    private List<ICombat> players = new ArrayList<ICombat>();

    ICombat player;
    ICombat opponent;

    public Combat(ICombat player, ICombat opponent){
        this.player = player;
        this.opponent = opponent;
        enterCombat(this.player, this.opponent);
    }

    public void enterCombat(ICombat player, ICombat opponent){
        players.add(this.player);
        players.add(this.opponent);
    }

    public boolean isCombat(){
        //TODO
        while(1 == 1 /*this.player.getHealth() > 0 || this.opponent.getHealth()*/ ){
            return true;
        }
    }

    public void removeFromCombat(ICombat player, ICombat opponent){
        if(!isCombat()){
            players.remove(player);
            players.remove(opponent);
        }
    }
}
