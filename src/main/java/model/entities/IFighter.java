package model.entities;
import model.effects.IEffectContainer;
import model.puckemon.IPuckemon;

/**
 * An Interface representing a fighter with common methods used in combat
 * @author Emil Jonsson
 */
public interface IFighter {

    /**
     * Makes a move during combat and returns the move if one is made. Can return null if no move is made!
     * @param enemyP the opposing Puckemon, meant to be used to make calculated moves
     * @return an IEffectContainer containing effects from the move chosen
     */
    IEffectContainer makeMove(IPuckemon enemyP);

    /**
     * Returns the Puckemon currently fighting
     * @return active puckemon as an IPuckemon
     */
    IPuckemon getActivePuckemon();

    /**
     * Checks if fighter is defeated
     * @return true if defeated, false if not
     */
    boolean checkIfDefeated();

}
