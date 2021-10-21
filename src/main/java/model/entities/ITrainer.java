package model.entities;

import model.effects.IEffectContainer;
import model.inventories.Item;

/**
 * An Interface representing a trainer
 * @author Emil Jonsson
 */
public interface ITrainer {

    /**
     * Switches Puckemon to the specified index in the PuckeBag
     * @param index the index where the Puckemon lies in the PuckeBag
     */
    void switchPuckemon(int index);

    /**
     * Returns the Item at the specified index in the Inventory
     * @param index the index where the Item lies in the inventory
     * @return the sought after Item
     */
    IEffectContainer getItem(int index);

    /**
     * Adds item to the Inventory
     * @param item the item to be added to the Inventory
     */
    void addItem(Item item);

}
