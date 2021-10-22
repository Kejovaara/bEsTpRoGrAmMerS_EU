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
        executeEffects(fighterMove, fighterPuckemon, playerPuckemon);
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

        checkDefeat();
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
