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
    }

    public void enterCombat(){
        players.add(this.player);
        players.add(this.opponent);
    }

    public void checkCombat(){
        for(ICombat combatPlayers : players){

        }
    }

    public void removeFromCombat(ICombat player, ICombat opponent){

    }
}
