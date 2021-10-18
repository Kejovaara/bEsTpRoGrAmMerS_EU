package model.entities;

import model.effects.IEffectContainer;
import model.inventories.Item;

public interface ITrainer {
    void switchPuckemon(int index);
    IEffectContainer getItem(int index);
    void addItem(Item item);

}
