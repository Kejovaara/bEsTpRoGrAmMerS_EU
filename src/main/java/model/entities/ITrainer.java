package model.entities;

import model.effects.IEffectContainer;
import model.inventories.Item;

public interface ITrainer {
//    public void switchPuckemon(int index);
    IEffectContainer getItem(int index);
    void addItem(Item item);

}
