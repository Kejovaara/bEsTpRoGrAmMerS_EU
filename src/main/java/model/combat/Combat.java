package model.combat;

import model.effects.IEffect;
import model.effects.IEffectContainer;
import model.entities.IFighter;
import model.entities.puckemon.IPuckemon;
import model.entities.Player;

import java.util.List;
import java.util.Random;

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


    private void checkDefeat(){
        if(fighter.checkIfDefeated()){
            victory();
        }
        if(player.checkIfDefeated()){
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

    /**
     * When fleeing, you admit defeat.
     */
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
        executeEffects(fighterMove, fighterPuckemon, playerPuckemon);
    }

    /**
     * The method for using an attack in game.
     * @param index is the index of the chosen attack.
     */
    public void usePlayerAttack(int index){
        IPuckemon playerPuckemon = player.getActivePuckemon();
        IEffectContainer playerAttack = player.getActivePuckemon().getAttack(index);

        IEffectContainer fighterMove = fighter.makeMove(playerPuckemon);
        IPuckemon fighterPuckemon = fighter.getActivePuckemon();

        System.out.println(fighterMove == null);
        //If enemy fighter made a move
        executeMoves(playerPuckemon,playerAttack,fighterPuckemon,fighterMove);

        checkDefeat();
    }

    private void executeMoves(IPuckemon playerPuckemon, IEffectContainer playerMove, IPuckemon fighterPuckemon, IEffectContainer enemyMove){
        if (enemyMove != null) {
            //Check which move should be executed first
            if(playerMove.getPriority() < enemyMove.getPriority()){
                //player attacks first
                executeEffects(playerMove, playerPuckemon, fighterPuckemon);
                executeEffects(enemyMove, fighterPuckemon, playerPuckemon);
            }else if(playerMove.getPriority() > enemyMove.getPriority()){
                //enemy attacks first
                executeEffects(enemyMove, fighterPuckemon, playerPuckemon);
                executeEffects(playerMove, playerPuckemon, fighterPuckemon);
            }else{
                samePriority(playerPuckemon,playerMove,fighterPuckemon,enemyMove);
            }
        } else {
            //Only execute players attack
            executeEffects(playerMove, playerPuckemon, fighterPuckemon);
        }
    }

    private void samePriority(IPuckemon playerPuckemon, IEffectContainer playerMove, IPuckemon fighterPuckemon, IEffectContainer enemyMove){
        if(playerPuckemon.getSpeed()>fighterPuckemon.getSpeed()){
            //player attacks first
            executeEffects(playerMove, playerPuckemon, fighterPuckemon);
            executeEffects(enemyMove, fighterPuckemon, playerPuckemon);
        }else if(playerPuckemon.getSpeed()>fighterPuckemon.getSpeed()){
            //enemy attacks first
            executeEffects(enemyMove, fighterPuckemon, playerPuckemon);
            executeEffects(playerMove, playerPuckemon, fighterPuckemon);
        }else if(playerAttacksFirst()){
            //player attacks first
            executeEffects(playerMove, playerPuckemon, fighterPuckemon);
            executeEffects(enemyMove, fighterPuckemon, playerPuckemon);
        }else{
            //enemy attacks first
            executeEffects(enemyMove, fighterPuckemon, playerPuckemon);
            executeEffects(playerMove, playerPuckemon, fighterPuckemon);
        }
    }

    private boolean playerAttacksFirst(){
        Random r = new Random();
        return (r.nextInt(2)==1);
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

        executeMoves(playerPuckemon,item,fighterPuckemon,fighterMove);

        player.consumeItem(index);
        checkDefeat();
    }

    private void executeEffects(IEffectContainer effectContainer, IPuckemon attackUser, IPuckemon opponent){
        if (effectContainer != null) {
            List<IEffect> effects = effectContainer.getEffects();

            for(IEffect effect : effects){
                if(attackUser.getHealth() == 0){ //If IPuckemon is fainted it can't use attacks, items etc.
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
