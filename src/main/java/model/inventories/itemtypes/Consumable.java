package model.inventories.itemtypes;

import model.effects.IEffect;
import model.entities.ITrainer;

import java.util.ArrayList;
import java.util.List;

public class Consumable {
    private final List<IEffect> effects;

    public Consumable(final List<IEffect> effects) {
        this.effects = new ArrayList<>(effects);
    }

    public void use(ITrainer trainer){
        for(IEffect effect : this.effects){
            // effect.execute(trainer,);
        }
    }

}
