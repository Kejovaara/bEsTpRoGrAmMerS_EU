package model.entities.puckemon;

import model.PTypes;

import java.util.List;

/**
 * A OwnedPuckemon is a puckemon that can level up, have a nickName and evolve.
 * @author  Lukas Jigberg
 */
public class OwnedPuckemon extends Puckemon {

    private String nickName;
    private final int evolutionId;
    private final int evolutionLevel;
    private boolean evolve = false;
    private int expPoints;

    /**
     * The same as below but doesn't contain nickName.
     */
    public OwnedPuckemon(int id, int level, String name, List<PTypes> types, int baseHealth, int baseAttackPower, int baseDefence, int baseSpeed, int evolutionLevel, int evolutionId, List<String> moveList){
        super(id, level, name, types, baseHealth, baseAttackPower, baseDefence, baseSpeed, moveList);
        this.evolutionId = evolutionId;
        this.evolutionLevel = evolutionLevel;
        this.expPoints = (int) Math.pow(level,3);

    }

    /**
     * @param evolutionId is the puckemon it will evolve into.
     * @param evolutionLevel is the level that is required to evolve.
     * @param nickName is a Puckemons nickname.
     */
    public OwnedPuckemon(int id, int level, String name, List<PTypes> types, int baseHealth, int baseAttackPower, int baseDefence, int baseSpeed, int evolutionLevel, int evolutionId, List<String> moveList, String nickName){
        super(id, level, name, types, baseHealth, baseAttackPower, baseDefence, baseSpeed, moveList);
        this.evolutionId = evolutionId;
        this.evolutionLevel = evolutionLevel;
        this.nickName = nickName;
        this.expPoints = (int) Math.pow(level,3);
    }

    /**
     * Gives the Puckemon experience Point needed to level.
     * If the experience points are more than level^3 it will reach that level by level up.
     */
    private void gainExp(int experience){
        if (level<=100){
            expPoints += experience;
            while ((expPoints > (Math.pow((level+1),3))) && (level<100) ){
                levelUp();
            }
        }
    }

    /**
     * Increases the level, can't be more than 100.
     * It makes it so that it keeps the same maxHealth / currentHealth ratio as before it leveled.
     * If the Puckemon leveled and is now the same or above its evolutionLevel it will be able to evolve.
     */
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
    public String getNickName(){
        if(nickName!=null){
            return nickName;
        }else{return name;}
    }
    public boolean getEvolve(){return evolve;}
    public int getEvolutionId(){return evolutionId;}
    public void giveExp(int exp){gainExp(exp);}
}
