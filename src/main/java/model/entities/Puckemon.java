package model.entities;

import model.combat.Attack;
import model.effects.IEffectContainer;

import java.util.ArrayList;

public class Puckemon {

    private String name = "Puckemon";
    private String nickName;
//    private Type type1;
//    private Type type2;

    private int baseHealth;
    private int baseAttackPower;
    private int baseDefence;
    private int baseSpeed;

    private int evolutionLevel = 101;
    private int evolutionID = 0;

    private ArrayList<Attack> attacks = new ArrayList<Attack>();

    // -------------

    /**
     * These are a pokemons stats. They are solely based on the baseStats and current level.
     */
    private int maxHealth;
    private int attackPower;
    private int defence;
    private int speed;

    /**
     * These are a pokemons stats during the current combat. They can be altered.
     */
    private int currentHealth;
    private int currentAttackPower;
    private int currentDefence;
    private int currentSpeed;

    private int attackPowerBuffFactor = 0;
    private int defenceBuffFactor = 0;
    private int speedBuffFactor = 0;
    private int level;
    private int expPoints;


    public Puckemon(int level, int id){
        this.level = level;
        buildPuckemon(id);
    }

    public Puckemon(int level, int id, String nickName){
        this.level = level;
        this.nickName = nickName;
        buildPuckemon(id);
    }

    private void buildPuckemon(int id){
        // Collects data from Excel depending on the ID
        // ex: this.baseSpeed = "excel.baseSpeed id 1"
        calculateLevelStats();

    }

    private void calculateLevelStats(){
        this.maxHealth = (2*baseHealth+level)/100 + level + 10;
        this.attackPower = (2*baseAttackPower+level)/100+5;
        this.defence = (2*baseDefence+level)/100+5;
        this.speed = (2*baseSpeed+level)/100+5;
    }


    /**
     * base exp (68 - 220)ish * level * trainer ( 1,5) = gained exp
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
//           This should only happen after battle, not right in the middle
//            buildPuckemon(evolutionID);
        }else{
//            The pokemon keeps the same health percentage when it levels up.
            int healthPercentage = currentHealth/maxHealth;
            calculateLevelStats();
            currentHealth = maxHealth/healthPercentage;
        }
    }

    private void alterCurrentStats(){
        if (attackPowerBuffFactor < 0){
            currentAttackPower = (int) (attackPower) * (2 / (2 + (-1) * attackPowerBuffFactor));
        }else{
            currentAttackPower = (int) (attackPower * (1 + attackPowerBuffFactor * 0.25));
        }
        if (defenceBuffFactor < 0){
            currentDefence = (int) (defence) * (2 / (2 + (-1) * defenceBuffFactor));
        }else{
            currentDefence = (int) (defence * (1 + defenceBuffFactor * 0.25));
        }
        if (speedBuffFactor < 0){
            currentSpeed = (int) (speed) * (2 / (2 + (-1) * speedBuffFactor));
        }else{
            currentSpeed = (int) (speed * (1 + speedBuffFactor * 0.25));
        }
    }

    public IEffectContainer getAttack(int i) {
        return attacks.get(i);
    }


    public int getHealth() {
        return baseHealth;
    }

    public String getName() {
        return name;
    }
}
