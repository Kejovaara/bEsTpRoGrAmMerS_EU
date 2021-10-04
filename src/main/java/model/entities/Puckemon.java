package model.entities;

import model.MonRegisterInterpreter;
import model.PTypes;
import model.attack.Attack;

import java.util.ArrayList;

public abstract class Puckemon {

    protected String name;
    protected PTypes type1;
    protected PTypes type2;

    protected int baseHealth;
    protected int baseAttackPower;
    protected int baseDefence;
    protected int baseSpeed;

    protected int level;

    protected int evolutionLevel = 101;
    protected int evolutionID = 0;

    // -------------

    /**
     * These are a pokemons stats. They are solely based on the baseStats and current level.
     */
    protected int maxHealth;
    protected int attackPower;
    protected int defence;
    protected int speed;

    /**
     * These are a pokemons stats during the current combat. They can be altered.
     */
    protected int currentHealth;
    protected int currentAttackPower;
    protected int currentDefence;
    protected int currentSpeed;

    protected int attackPowerBuffFactor = 0;
    protected int defenceBuffFactor = 0;
    protected int speedBuffFactor = 0;

    protected ArrayList<Attack> moveList = new ArrayList<Attack>();
    protected ArrayList<Attack> moveSet = new ArrayList<Attack>(4);
    private MonRegisterInterpreter monRegisterInterpreter = new MonRegisterInterpreter();

    public Puckemon(int id, int level){
        buildPuckemon(id);
        this.level = level;
    }

    protected void buildPuckemon(int id){
        this.name = monRegisterInterpreter.getName(id);
        this.type1 = monRegisterInterpreter.getType1(id);
        this.type2 = monRegisterInterpreter.getType2(id);
        this.baseHealth = monRegisterInterpreter.getBaseHealth(id);
        this.baseAttackPower = monRegisterInterpreter.getBaseAttack(id);
        this.baseDefence = monRegisterInterpreter.getBaseDefence(id);
        this.baseSpeed = monRegisterInterpreter.getBaseSpeed(id);
        this.evolutionLevel = monRegisterInterpreter.getEvolutionLevel(id);
        this.evolutionID = monRegisterInterpreter.getEvolutionId(id);
        System.out.println(name);
        calculateLevelStats();

    }

    protected void calculateLevelStats(){
        this.maxHealth = (2*baseHealth+level)/100 + level + 10;
        this.attackPower = (2*baseAttackPower+level)/100+5;
        this.defence = (2*baseDefence+level)/100+5;
        this.speed = (2*baseSpeed+level)/100+5;
    }

    protected void alterCurrentStats(){
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

//    public IEffectContainer getAttack(int i) {
//        return moveSet.get(i);
//    }


    public int getHealth() {
        return baseHealth;
    }

    public String getName() {
        return name;
    }
}
