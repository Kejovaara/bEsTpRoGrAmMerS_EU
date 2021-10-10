package model.effects;

import model.PTypes;
import model.entities.IPuckemon;

import java.util.Random;

public class EffectHelper {
    private static PTypes[] types= {PTypes.NORMAL, PTypes.FIRE, PTypes.WATER, PTypes.ELECTRIC, PTypes.GRASS, PTypes.ICE, PTypes.FIGHTING, PTypes.POISON, PTypes.GROUND, PTypes.FLYING, PTypes.PSYCHIC, PTypes.BUG, PTypes.ROCK, PTypes.GHOST, PTypes.DRAGON, PTypes.DARK, PTypes.STEEL, PTypes.FAIRY};

    private static double[][] typeChart = {
            //1    2    3    4    5    6    7    8    9   10   11   12   13   14   15   16   17   18
            {1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 0.0, 1.0, 1.0, 0.5, 1.0},
            {1, 0.5, 0.5, 1.0, 2.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 0.5, 1.0, 2.0, 1.0},
            {1, 2.0, 0.5, 1.0, 0.5, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.5, 1.0, 1.0, 1.0},
            {1, 1.0, 2.0, 0.5, 0.5, 1.0, 1.0, 1.0, 0.0, 2.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0},
            {1, 0.5, 2.0, 1.0, 0.5, 1.0, 1.0, 0.5, 2.0, 0.5, 1.0, 0.5, 2.0, 1.0, 0.5, 1.0, 0.5, 1.0},
            {1, 0.5, 0.5, 1.0, 2.0, 0.5, 1.0, 1.0, 2.0, 2.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.5, 1.0},
            {2, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.5, 1.0, 0.5, 0.5, 0.5, 2.0, 0.0, 1.0, 2.0, 2.0, 0.5},
            {1, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 0.5, 0.5, 1.0, 1.0, 1.0, 0.5, 0.5, 1.0, 1.0, 0.0, 2.0},
            {1, 2.0, 1.0, 2.0, 0.5, 1.0, 1.0, 2.0, 1.0, 0.0, 1.0, 0.5, 2.0, 1.0, 1.0, 1.0, 2.0, 1.0},
            {1, 1.0, 1.0, 0.5, 2.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 1.0, 1.0, 0.5, 1.0},
            {1, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 2.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 0.0, 0.5, 1.0},
            {1, 0.5, 1.0, 1.0, 2.0, 1.0, 0.5, 0.5, 1.0, 0.5, 2.0, 1.0, 1.0, 0.5, 1.0, 2.0, 0.5, 0.5},
            {1, 2.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 0.5, 2.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0},
            {0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 2.0, 1.0, 0.5, 1.0, 1.0},
            {1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.5, 0.0},
            {1, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 2.0, 1.0, 0.5, 1.0, 0.5},
            {1, 0.5, 0.5, 0.5, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 0.5, 2.0},
            {1, 0.5, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 2.0, 0.5, 1.0},

    };

    public static double getMultplier(PTypes attackType, PTypes defendType1, PTypes defendType2){
        int attackIndex = getIndex(attackType);
        int defendIndex1 = getIndex(defendType1);
        int defendIndex2 = getIndex(defendType2);
        double multiplier = typeChart[attackIndex][defendIndex1] * typeChart[attackIndex][defendIndex2];
        return multiplier;
    }

    //error hantering senare kanske;
    private static int getIndex(PTypes type){
        int index = 0;
        for(int i = 0; i < types.length; i++){
            if(types[i] == type){
                index = i;
                break;
            }
        }
        return index;
    }

    public static int calculateDamage(IPuckemon attackUser, IPuckemon opponent, int power, PTypes attackType){
        double dividendPart = ((float)((attackUser.getLevel() * 2)/5) + 2) * power * ((float)attackUser.getAttackPower()/(float)opponent.getDefence());
        double dividePart = (float)(dividendPart / 50) + 2;
        double damage = dividePart * randomFactor() * getMultplier(attackType,opponent.getType1(),opponent.getType2())*STABFactor(attackUser.getType1(),attackUser.getType2(), attackType);
        return (int)Math.round(damage);
    }

    private static double randomFactor(){
        double high = 1.5;
        double low = 1;
        Random r = new Random();
        return r.nextDouble() * (high-low) + low;
    }

    private static double STABFactor(PTypes puckemonType1, PTypes puckemonType2, PTypes attackType){
        if(puckemonType1 == attackType || puckemonType2 == attackType){
            return 1.5;
        }else{
            return 1.0;
        }
    }
}
