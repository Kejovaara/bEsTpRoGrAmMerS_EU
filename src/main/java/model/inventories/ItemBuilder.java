package model.inventories;

import model.effects.IEffect;
import model.effects.effectTypes.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A class that builds items.
 * @author André Kejovaara
 */
public class ItemBuilder {

    /**
     * @return A random item.
     */
    public static Item getRandom(){
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomIndex = ThreadLocalRandom.current().nextInt(0, Items.values().length);
        return getItem(randomIndex);
    }

    /**
     * @param name the enum Items of the item
     * @return the item requested
     */
    public static Item getItem(Items name){
        switch(name){
            case MINOR_HEALING_POTION:
                return getMinorHealingPotion();
            case MAJOR_HEALING_POTION:
                return getMajorHealingPotion();
            case ETHER_POTION:
                return getEtherPotion();
            case GOLDEN_NUGGIE:
                return getGoldenNuggie();
            case SILVER_NUGGIE:
                return getSilverNuggie();
            case SUPER_KILL_POTION:
                return getSuperKillPotion();
            default: throw new IllegalArgumentException("Wrong Enum");
        }
    }

    private static Item getItem(int index){
        return getItem(Items.values()[index]);
    }

    private static Item getMinorHealingPotion(){
        List<IEffect> effects = new ArrayList<>();
        effects.add(new HealAmount(40));
        return new Item(1,"Minor Healing Potion","Restores 50 HP",1,1,5,effects);
    }

    private static Item getMajorHealingPotion(){
        List<IEffect> effects = new ArrayList<>();
        effects.add(new HealAmount(100));
        return new Item(2,"Major Healing Potion","Restores 100 HP",1,1,5,effects);
    }

    private static Item getEtherPotion(){
        List<IEffect> effects = new ArrayList<>();
        effects.add(new RestorePP(10));
        return new Item(3,"Ether Potion","Restores PP on all the attacks of the active Puckemon by maximum 10",1,1,10,effects);
    }

    private static Item getGoldenNuggie(){
        List<IEffect> effects = new ArrayList<>();
        effects.add(new NoEffect(new Item(4,"Golden Nuggie","A big nuggie of pure gold that gives off a lustrous gleam. Only a maniac will buy it for its high price.",5,1,100)));
        return new Item(4,"Golden Nuggie","A big nuggie of pure gold that gives off a lustrous gleam. Only a maniac will buy it for its high price.",5,1,100,effects);
    }

    private static Item getSilverNuggie(){
        List<IEffect> effects = new ArrayList<>();
        effects.add(new NoEffect(new Item(5,"Silver Nuggie","A big nuggie of pure silver that gives off a lustrous gleam. Only a maniac will buy it for its high price.",5,1,35)));
        return new Item(5,"Silver Nuggie","A big nuggie of pure silver that gives off a lustrous gleam. Only a maniac will buy it for its high price.",5,1,35,effects);
    }

    private static Item getSuperKillPotion(){
        List<IEffect> effects = new ArrayList<>();
        effects.add(new DoDamagePercentage(100f));
        return new Item(6,"Super Kill Potion", "An item that instantly kills your opponent",1,1,20,effects);
    }
}
