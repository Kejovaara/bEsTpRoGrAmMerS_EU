package model.entities;

import model.PTypes;

import java.util.ArrayList;
import java.util.List;

public class OwnedPuckemon extends Puckemon {

    private String nickName;
    private int evolutionId;
    private int evolutionLevel;

    private int expPoints;


    public OwnedPuckemon(int id, int level, String name, List<PTypes> types, int baseHealth, int baseAttackPower, int baseDefence, int baseSpeed, int evolutionLevel, int evolutionId, List<String> moveList){
        super(id, level, name, types, baseHealth, baseAttackPower, baseDefence, baseSpeed, moveList);
        this.evolutionId = evolutionId;
        this.evolutionLevel = evolutionLevel;

    }

    public OwnedPuckemon(int id, int level, String name, List<PTypes> types, int baseHealth, int baseAttackPower, int baseDefence, int baseSpeed, int evolutionLevel, int evolutionId, List<String> moveList, String nickName){
        super(id, level, name, types, baseHealth, baseAttackPower, baseDefence, baseSpeed, moveList);
        this.evolutionId = evolutionId;
        this.evolutionLevel = evolutionLevel;
        this.nickName = nickName;
    }

    private void getMoves(){

    }

    private void preformAttack(){

    }


    /**
     * 100 * level * trainer ( 1,5) = gained exp
     * expPoints needed to reach level x is x^3
     */
    private void gainExp(int experience){
        if (expPoints < (100^3)){
            expPoints += experience;
            while (expPoints > ((level+1)^3)){
                levelUp();
            }
        }
    }

    private void levelUp(){
        if (level < 100){
            level++;
        }
        if (level >= evolutionLevel) {
//            evolve
//           This should only happen after battle, not right in the middle
//            buildPuckemon(evolutionID);
        }else{
//            The pokemon keeps the same health percentage when it levels up.
            int healthPercentage = currentHealth/maxHealth;
            calculateLevelStats();
            currentHealth = maxHealth/healthPercentage;
        }
    }

    public void setNickName(String nickName){this.nickName=nickName;}
}
