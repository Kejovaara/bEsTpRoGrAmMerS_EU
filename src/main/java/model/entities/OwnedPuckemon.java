package model.entities;

import java.util.ArrayList;

public class OwnedPuckemon extends Puckemon {

    private String nickName;

    private int expPoints;


    public OwnedPuckemon(int level, int id){
        super(id,level);

    }

    public OwnedPuckemon(int level, int id, String nickName){
        super(id,level);
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


    public int getHealth() {
        return baseHealth;
    }
    public String getName() {
        return name;
    }
    public void setNickName(String nickName){this.nickName=nickName;}
}
