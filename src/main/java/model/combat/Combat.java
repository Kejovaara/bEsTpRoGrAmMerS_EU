package model.combat;

import model.effects.IEffect;
import model.effects.IEffectContainer;
import model.entities.IFighter;
import model.entities.IPuckemon;
import model.entities.ITrainer;
import model.entities.Player;

import java.util.ArrayList;
import java.util.List;

public class Combat {

    private List<IFighter> players = new ArrayList<IFighter>();

    IFighter fighter1;
    IFighter fighter2;

    IFighter fighter;
    Player player;

    private List<IEffect> playerDOTEffects = new ArrayList<>();
    private List<IEffect> opponentDOTEffects = new ArrayList<>();



    public Combat(IFighter fighter1, IFighter fighter2){
        this.fighter1 = fighter1;
        this.fighter2 = fighter2;
        enterCombat(this.fighter1, this.fighter2);
    }

    public Combat(Player player, IFighter fighter){
        this.player = player;
        this.fighter = fighter;
    }

    public void enterCombat(IFighter player, IFighter opponent){
        players.add(this.fighter1);
        players.add(this.fighter2);
    }

    /*public void doTurn(){
        IEffectContainer f1Move = fighter1.makeMove();
        IPuckemon f1Puckemon = fighter1.getActivePuckemon();
        IEffectContainer f2Move = fighter2.makeMove();
        IPuckemon f2Puckemon = fighter2.getActivePuckemon();


        if(f1Move.getPriority() < f2Move.getPriority()){
            executeEffects(f1Move.getEffects(), f1Puckemon, f2Puckemon);
            executeEffects(f2Move.getEffects(), f2Puckemon, f1Puckemon);
        }else if(f1Move.getPriority() > f2Move.getPriority()){
            executeEffects(f2Move.getEffects(), f2Puckemon, f1Puckemon);
            executeEffects(f1Move.getEffects(), f1Puckemon, f2Puckemon);
        }


    }*/

    public void usePlayerAttack(int index){
    }

    private void executeEffects(List<IEffect> effects, IPuckemon attackUser, IPuckemon opponent){
        for(IEffect effect : effects){
            if(attackUser.getHealth() == 0){
                break;
            }
            effect.execute(attackUser, opponent);
        }
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
