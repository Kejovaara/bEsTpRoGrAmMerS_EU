package model.entities;

import model.effects.IEffectContainer;
import model.inventories.Item;

public interface ITrainer {
//    public void switchPuckemon(int index);
    public IEffectContainer getItem(int index);
    public void addItem(Item item);

}
