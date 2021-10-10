package model.inventories;

import model.effects.IEffect;
import model.effects.effectTypes.*;

import java.util.ArrayList;
import java.util.List;

public class ItemFactory {


    public static Item getSmallHealthPotion(){
        List<IEffect> effects = new ArrayList<>();
        effects.add(new HealAmount(40));
        return null;
    }

    public static Item getMajorHealthPotion(){
        List<IEffect> effects = new ArrayList<>();
        effects.add(new HealAmount(75));
        return null; //new Item("Major Healing Potion")
    }
}
