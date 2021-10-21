package model.effects;

import model.entities.IPuckemon;

import java.util.List;

/**
 * An Interface that represents a list of IEffect objects.
 * @author Rasmus Almryd
 */
public interface IEffectContainer {

    /**
     * Used to decide which IEffectContainer is executed first.
     * @return the priority of this collection of effects.
     */
    int getPriority();

    /**
     * @return the list of IEffects
     */
    List<IEffect> getEffects();

    /**
     * Executes all IEffect objects that is contained in this object.
     * @param attackUser the Ipuckemon that used the effect
     * @param opponent the opposing IPuckemon
     */
    void execute(IPuckemon attackUser, IPuckemon opponent);
}
