package model.entities.puckemon;

import model.PTypes;
import model.attack.Attack;
import model.effects.IEffectContainer;

import java.util.List;

/**
 * Interface for Puckemons
 * @author Lukas Jigberg
 * @author Emil Jonsson
 */
public interface IPuckemon {

    /**
     * @return the name of the puckemon
     */
    String getName();

    /**
     * @return returns the id of a puckemon
     */
    int getId();

    /**
     * Used to do damage to this Puckemon.
     * @param damage amount of damage.
     */
    void doDamage(int damage);
    /**
     * Used to heal this Puckemon.
     * @param heal amount to heal.
     */
    void heal(int heal);

    /**
     * Gets health.
     * @return amount of health.
     */
    int getHealth();
    /**
     * @return the maximum health the puckemon can have.
     */
    int getMaxHealth();
    /**
     * For future update. Make it not possible to change health.
     */
    void lockHealth();

    /**
     * @return the puckemons speed.
     */
    int getSpeed();

    /**
     * For future update. Make it not possible to change speed
     * @see Puckemon
     */
    void lockSpeed();

    /**
     * Changes speed said amount.
     * @param buffFactor amount to change speed.
     */
    void modifySpeed(int buffFactor);


    /**
     * Gets attack power of this Puckemon.
     * @return amount of attack power.
     */
    int getAttackPower();

    /**
     * For future update. Make it not possible to change speed
     * @see Puckemon
     */
    void lockAttackPower();

    /**
     * Changes speed said amount.
     * @param buffFactor amount to change speed.
     */
    void modifyAttackPower(int buffFactor);

    int getDefence();
    /**
     * For future update. Make it not possible to change speed
     * @see Puckemon
     */
    void lockDefence();
    /**
     * Changes defence said amount.
     * @param buffFactor amount to change defence.
     */
    void modifyDefence(int buffFactor);

    /**
     * @return the attacks the Puckemon has
     */
    List<Attack> getMoveSet();
    /**
     * @param i the index of the attack
     * @return the specified attack on the given index.
     */
    IEffectContainer getAttack(int i);

    /**
     * @return the type o
     */
    List<PTypes> getTypes();

    /**
     * Level is used to calculate stats, evolve etc.
     * @return The Puckemons level
     */
    int getLevel();
}
