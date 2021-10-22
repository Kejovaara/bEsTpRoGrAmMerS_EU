package model.combat;

import model.effects.IEffect;
import model.effects.IEffectContainer;
import model.entities.IFighter;
import model.entities.IPuckemon;
import model.entities.Player;

import java.util.List;

public class Combat {

    IFighter fighter;
    Player player;
    private String battleOutcome = "Ongoing";

    /**
     * Constructor for combat
     * @param player is the user in the game.
     * @param fighter is the opponent in the game.
     */
    public Combat(Player player, IFighter fighter){
        this.player = player;
        this.fighter = fighter;
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
    private void checkDefeat(){
        if(fighter.checkIfDefeated()){
            System.out.println("You Won :)");
            victory();
        }
        if(player.checkIfDefeated()){
            System.out.println("You Lost :(");
            defeat();
        }
    }

    private void victory(){
        player.victoryEvent();
        battleOutcome = "Victory";
    }

    private void defeat(){
        battleOutcome = "Defeat";
    }

    public void useFlee(){
        defeat();
    }

    /**
     * The method for switching Puckemons in the game.
     */
    public void useSwitch(){
        IPuckemon fighterPuckemon = fighter.getActivePuckemon();
        IPuckemon playerPuckemon = player.getActivePuckemon();
        IEffectContainer fighterMove = fighter.makeMove(playerPuckemon);
        int pdiff = playerPuckemon.getHealth();
        executeEffects(fighterMove, fighterPuckemon, playerPuckemon);
        System.out.println("player switched, player: " + (pdiff-playerPuckemon.getHealth()));
    }

    /**
     * The method for using an attack in game.
     * @param index is the index of the chosen attack.
     */
    public void usePlayerAttack(int index){
        IPuckemon playerPuckemon = player.getActivePuckemon();
        IEffectContainer attack = player.getActivePuckemon().getAttack(index);

        IEffectContainer fighterMove = fighter.makeMove(playerPuckemon);
        IPuckemon fighterPuckemon = fighter.getActivePuckemon();

        int pdiff = playerPuckemon.getHealth();
        int fdiff = fighterPuckemon.getHealth();

        //If enemy fighter made a move
        if (fighterMove != null) {
            //Check which move should be executed first
            if(attack.getPriority() < fighterMove.getPriority()){
                executeEffects(attack, playerPuckemon, fighterPuckemon);
                executeEffects(fighterMove, fighterPuckemon, playerPuckemon);
            }else{
                executeEffects(fighterMove, fighterPuckemon, playerPuckemon);
                executeEffects(attack, playerPuckemon, fighterPuckemon);
            }
        } else {
            //Only execute players attack
            executeEffects(attack, playerPuckemon, fighterPuckemon);
        }
        System.out.println("player: " + (pdiff-playerPuckemon.getHealth()) + ", fighter: " + (fdiff-fighterPuckemon.getHealth()));
        checkDefeat();
//        System.out.println(playerPuckemon.getName()+" used "+player.getPuckemon().getAttack(index).get);
//        System.out.println(fighterPuckemon.getName()+" used "+fighterPuckemon.getA);
    }

    /**
     * The method for using an item in game.
     * @param index index of chosen item.
     */
    public void usePlayerItem(int index){
        IPuckemon playerPuckemon = player.getActivePuckemon();
        IEffectContainer item = player.getItem(index);

        IEffectContainer fighterMove = fighter.makeMove(playerPuckemon);
        IPuckemon fighterPuckemon = fighter.getActivePuckemon();

        int pdiff = playerPuckemon.getHealth();
        int fdiff = fighterPuckemon.getHealth();

        //If enemy fighter made a move
        if (fighterMove != null) {
            //Check which move should be executed first
            if(item.getPriority() < fighterMove.getPriority()){
                executeEffects(item, playerPuckemon, fighterPuckemon);
                executeEffects(fighterMove, fighterPuckemon, playerPuckemon);
            }else{
                executeEffects(fighterMove, fighterPuckemon, playerPuckemon);
                executeEffects(item, playerPuckemon, fighterPuckemon);
            }
        } else {
            //Only execute players attack
            executeEffects(item, playerPuckemon, fighterPuckemon);
        }

        player.consumeItem(index);
        checkDefeat();
        System.out.println("player: " + (pdiff-playerPuckemon.getHealth()) + ", fighter: " + (fdiff-fighterPuckemon.getHealth()));
//        System.out.println(playerPuckemon.getName()+" used "+player.getPuckemon().getAttack(index).get);
//        System.out.println(fighterPuckemon.getName()+" used "+fighterPuckemon.getA);
    }

    private void executeEffects(IEffectContainer effectContainer, IPuckemon attackUser, IPuckemon opponent){
        if (effectContainer != null) {
            List<IEffect> effects = effectContainer.getEffects();

            for(IEffect effect : effects){
                if(attackUser.getHealth() == 0){
                    break;
                }
                if (effect != null) {
                    effect.execute(attackUser, opponent);
                }
            }
        }
    }

    /**
     * @return the outcome of the game.
     */
    public String getBattleOutcome(){return battleOutcome;}

}
