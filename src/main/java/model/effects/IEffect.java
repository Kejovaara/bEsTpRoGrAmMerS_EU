package model.effects;

import model.puckemon.IPuckemon;

/**
 * An Interface that specifies common a method for all effects. An object of type IEffect can execute some
 * calculation/modification on IPuckemon's
 * @author Rasmus Almryd
 */
public interface IEffect {

    /**
     * Takes in two IPuckemon's and does som calculation/modification of them
     * @param attackUser the IPuckemon that used the effect
     * @param opponent the opposing IPuckemon
     */
    void execute(IPuckemon attackUser, IPuckemon opponent);
}
