package model.effects;

import model.PTypes;
import model.puckemon.IPuckemon;

import java.util.List;
import java.util.Random;

public class EffectHelper {
    //the order in which the floats in type diagrams have been laid out.
    private final static PTypes[] types= {PTypes.NORMAL, PTypes.FIRE, PTypes.WATER, PTypes.ELECTRIC, PTypes.GRASS,
                                        PTypes.ICE, PTypes.FIGHTING, PTypes.POISON, PTypes.GROUND, PTypes.FLYING,
                                        PTypes.PSYCHIC, PTypes.BUG, PTypes.ROCK, PTypes.GHOST, PTypes.DRAGON, PTypes.DARK,
                                        PTypes.STEEL, PTypes.FAIRY};


    // The chart is used to determine the damage effect of a type match up.
    //The horizontal type attacks the vertical type. Position (2,3) is 2.0 which means that attacking type (3) is super effective (2.0) against defending type (2).
    private final static double[][] typeChart = {
            //1    2    3    4    5    6    7    8    9   10   11   12   13   14   15   16   17   18  <= Defender index
            {1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 0.0, 1.0, 1.0, 0.5, 1.0},// Attacker index
            {1, 0.5, 0.5, 1.0, 2.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 0.5, 1.0, 2.0, 1.0},//      ||
            {1, 2.0, 0.5, 1.0, 0.5, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.5, 1.0, 1.0, 1.0},//      \/
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

    /**
     * Gets the multiplier (The effectiveness) for the attack depending on the defending Puckemons types.
     * @param attackType type of the attack meant to be executed.
     * @param defendTypes types of the defending Puckemon.
     * @return a multiplier meant to show the effectiveness of the attack based on types.
     */
    public static double getMultiplier(PTypes attackType, List<PTypes> defendTypes){
        int attackIndex = getIndex(attackType);
        int defendIndex1 = getIndex(defendTypes.get(0));
        int defendIndex2 = getIndex(defendTypes.get(defendTypes.size()-1));

        if (defendIndex1!=defendIndex2){
            return typeChart[attackIndex][defendIndex1] * typeChart[attackIndex][defendIndex2];
        }else{
            return typeChart[attackIndex][defendIndex1];
        }
    }


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

    /**
     * Calculates the damage an effect makes on the opponent based on the Attack type and Puckemons involved.
     * @param attackUser the Puckemon performing the damage.
     * @param opponent the Puckemon receiving the damage.
     * @param power the power of the effect.
     * @param attackType the type of the attack.
     * @return the damage to be dealt the opponent Puckemon.
     */
    public static int calculateDamage(IPuckemon attackUser, IPuckemon opponent, int power, PTypes attackType){
        double dividendPart = ((float)((attackUser.getLevel() * 2)/5) + 2) * power * ((float)attackUser.getAttackPower()/(float)opponent.getDefence());
        double dividePart = (float)(dividendPart / 50) + 2;
        double damage = dividePart * randomFactor() * getMultiplier(attackType,opponent.getTypes())*STABFactor(attackUser.getTypes(), attackType);
        return (int)Math.round(damage);
    }

    private static double randomFactor(){
        double high = 1.2;
        double low = 1;
        Random r = new Random();
        return r.nextDouble() * (high-low) + low;
    }

    //Same Type Attack Bonus Factor, puckemon gets slight advantage if it uses attack with the same type as itself
    private static double STABFactor(List<PTypes> types, PTypes attackType){
        if(types.get(0) == attackType || types.get(types.size()-1) == attackType){
            return 1.5;
        }else{
            return 1.0;
        }
    }
}
