package model.inventories;

import model.effects.IEffect;
import model.effects.effectTypes.*;
import org.apache.pdfbox.contentstream.operator.state.Restore;

import java.util.ArrayList;
import java.util.List;

public class ItemFactory {


    public static Item getSmallHealingPotion(){
        List<IEffect> effects = new ArrayList<>();
        effects.add(new HealAmount(40));
        return new Item(1,"Minor Healing Potion","Restores 50 HP",1,1,5, true,effects);
    }

    public static Item getMajorHealingPotion(){
        List<IEffect> effects = new ArrayList<>();
        effects.add(new HealAmount(75));
        return new Item(2,"Major Healing Potion","Restores 100 HP",1,1,5,true,effects);
    }

    public static Item getEtherPotion(){
        List<IEffect> effects = new ArrayList<>();
        effects.add(new RestorePP(10));
        return new Item(3,"Ether Potion","Restores PP on all the attacks of the active Puckemon by 10",1,1,10,true,effects);
    }

    public static Item getGoldenNuggie(){
        return new Item(4,"Golden Nuggie","A big nuggie of pure gold that gives off a lustrous gleam. Only a maniac will buy it for its high price.",5,1,100,false);
    }

    public static Item getSilverNuggie(){
        return new Item(5,"Silver Nuggie","A big nuggie of pure silver that gives off a lustrous gleam. Only a maniac will buy it for its high price.",5,1,35,false);
    }

}
