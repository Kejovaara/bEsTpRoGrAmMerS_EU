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

    public static Item getRandom(){
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomIndex = ThreadLocalRandom.current().nextInt(0, INames.values().length);
        return getItem(randomIndex);
    }

    public static Item getItem(INames name){
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
            case EVOLVE_NUGGIE:
                return getEvolveNuggie();
            default: throw new IllegalArgumentException("Wrong Enum "+name);
        }
    }

    private static Item getItem(int index){
        return getItem(INames.values()[index]);
    }

    public static Item getMinorHealingPotion(){
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
        return new Item(3,"Ether Potion","Restores PP on all the attacks of the active Puckemon by maximum 10",1,1,10,true,effects);
    }

    public static Item getGoldenNuggie(){
        List<IEffect> effects = new ArrayList<>();
        effects.add(new NoEffect(new Item(4,"Golden Nuggie","A big nuggie of pure gold that gives off a lustrous gleam. Only a maniac will buy it for its high price.",5,1,100,false)));
        return new Item(4,"Golden Nuggie","A big nuggie of pure gold that gives off a lustrous gleam. Only a maniac will buy it for its high price.",5,1,100,false,effects);
    }

    public static Item getSilverNuggie(){
        List<IEffect> effects = new ArrayList<>();
        effects.add(new NoEffect(new Item(5,"Silver Nuggie","A big nuggie of pure silver that gives off a lustrous gleam. Only a maniac will buy it for its high price.",5,1,35,false)));
        return new Item(5,"Silver Nuggie","A big nuggie of pure silver that gives off a lustrous gleam. Only a maniac will buy it for its high price.",5,1,35,false,effects);
    }

    public static Item getSuperKillPotion(){
        List<IEffect> effects = new ArrayList<>();
        effects.add(new DoDamagePercentage(100f));
        return new Item(6,"Super Kill Potion", "An item that instantly kills your opponent",1,1,20,true,effects);
    }

    public static Item getEvolveNuggie(){
        List<IEffect> effects = new ArrayList<>();
        effects.add(new GiveXP(1000)); //SPECIFY BETTER LATER, ONLY FOR TESTING
        return new Item(7, "Evolve Nuggie", "Evolve your Puckemon to the next more powerful Puckemon",1,1,15,false,effects);
    }

    public enum INames{
        MINOR_HEALING_POTION,
        MAJOR_HEALING_POTION,
        ETHER_POTION,
        GOLDEN_NUGGIE,
        SILVER_NUGGIE,
        SUPER_KILL_POTION,
        EVOLVE_NUGGIE,
    }
}
