package model.entities;

import model.PTypes;
import java.util.List;

public class OwnedPuckemon extends Puckemon {

    private String nickName;
    private final int evolutionId;
    private final int evolutionLevel;
    private boolean evolve = false;
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

    /**
     * 100 * level * trainer ( 1,5) = gained exp
     * expPoints needed to reach level x is x^3
     */
    private void gainExp(int experience){
        if (level<=100){
            expPoints += experience;
            while ((expPoints > (Math.pow((level+1),3))) && (level<100) ){
                levelUp();
            }
        }
    }

    private void levelUp(){
        if (level < 100){
            level++;
            //The puckemon keeps the same health percentage when it levels up.
            double healthPercentage = ((double) currentHealth/(double) maxHealth);
            calculateLevelStats();
            if(healthPercentage!=0){
            currentHealth = (int) (maxHealth*healthPercentage);
            }

            if (level >= evolutionLevel) {
                evolve = true;
            }

        }
    }

    public int getEvolutionLevel(){return evolutionLevel;}
    public void setNickName(String nickName){this.nickName=nickName;}
    public String getNickName(){return nickName;}
    public boolean getEvolve(){return evolve;}
    public int getEvolutionId(){return evolutionId;}
    public void giveExp(int exp){gainExp(exp);}
}
