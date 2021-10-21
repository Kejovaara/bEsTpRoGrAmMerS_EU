package model.entities;

import model.PTypes;
import model.effects.IEffectContainer;

import java.util.List;
import java.util.Random;

/**
 * A class representing a wild Puckemon meant to function as an opponent in the game similar to the players Puckemon.
 * The difference from an OwnedPuckemon is that it cannot level up, have a nickName nor evolve.
 * @author Lukas Jigberg
 * @author Emil Jonsson
 */
public class VildPuckemon extends Puckemon implements IFighter{

    /**
     * Constructor of VildPuckemon, the same as Puckemon.
     * @param id the ID of the Puckemon
     * @param level the level of the Puckemon
     * @param name the name of the Puckemon
     * @param types list of 1-2 Puckemon PTypes that the Puckemon has. Used in combat
     * @param baseHealth amount of base health points
     * @param baseAttackPower amount of base attack power
     * @param baseDefence amount of base defence
     * @param baseSpeed amount of base speed
     * @param moveList a list of string with all the attacks the Puckemon can use
     */
    public VildPuckemon(int id, int level, String name, List<PTypes> types, int baseHealth, int baseAttackPower,
                        int baseDefence, int baseSpeed, List<String> moveList) {
        super(id, level, name, types, baseHealth, baseAttackPower, baseDefence, baseSpeed, moveList);
    }

    /**
     * Makes a move during combat and returns the move if one is made. For now returns a random available attack
     * @param enemyP the opposing Puckemon, can be used to make calculated move
     * @return an IEffectContainer containing effects from the move chosen
     */
    public IEffectContainer makeMove(IPuckemon enemyP) {
        Random rand = new Random(); //instance of random class
        int upperbound = this.moveSet.size();
        int int_random = rand.nextInt(upperbound);

        return getAttack(int_random);
    }

    /**
     * Simply checks if the health of the Puckemon is 0 or below, which means that it is defeated.
     * @return true if the Puckemon is defeated, false if not.
     */
    @Override
    public boolean checkIfDefeated(){
        return currentHealth <= 0;
    }

    @Override
    public IPuckemon getActivePuckemon() {
        return null;
    }

}
