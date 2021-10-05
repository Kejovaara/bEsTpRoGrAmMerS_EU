package model.entities;

import model.MonRegisterInterpreter;
import model.PTypes;
import model.attack.Attack;
import model.attack.AttackFactory;
import model.effects.IEffectContainer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public abstract class Puckemon implements IPuckemon {

    protected int id;
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

    protected boolean lockHealth = false;
    protected boolean lockAttackPower = false;
    protected boolean lockDefence = false;
    protected boolean lockSpeed = false;

    protected ArrayList<String> moveList = new ArrayList<String>();
    protected ArrayList<Attack> moveSet = new ArrayList<Attack>(4);
    private MonRegisterInterpreter monRegisterInterpreter = new MonRegisterInterpreter();

    public Puckemon(int id, int level){
        this.id = id;
        buildPuckemon(id);
        fillMoveSet();
        System.out.println(Arrays.toString(moveSet.toArray()));

        this.level = level;
    }

    protected void buildPuckemon(int id){
        this.id = id;
        this.name = monRegisterInterpreter.getName(id);
        this.type1 = monRegisterInterpreter.getType1(id);
        this.type2 = monRegisterInterpreter.getType2(id);
        this.baseHealth = monRegisterInterpreter.getBaseHealth(id);
        this.baseAttackPower = monRegisterInterpreter.getBaseAttack(id);
        this.baseDefence = monRegisterInterpreter.getBaseDefence(id);
        this.baseSpeed = monRegisterInterpreter.getBaseSpeed(id);
        this.evolutionLevel = monRegisterInterpreter.getEvolutionLevel(id);
        this.evolutionID = monRegisterInterpreter.getEvolutionId(id);
        this.moveList = monRegisterInterpreter.getMoveList(id);
        System.out.println(name);
        calculateLevelStats();
    }

    protected void fillMoveSet(){
        if (moveList.size() <= 4){
            for (int i = 0; i < moveList.size(); i++){
                moveSet.add(AttackFactory.createByName(moveList.get(i)));
            }
        } else{
            ArrayList<String> randList = new ArrayList<>(moveList);
            Collections.shuffle(randList);
            for (int i = 0; i < 3; i++) {
                moveSet.add(AttackFactory.createByName(moveList.get(i)));
            }
        }
    }

    protected void unlockStats(){
        this.lockHealth = false;
        this.lockAttackPower = false;
        this.lockDefence = false;
        this.lockSpeed = false;
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

    public IEffectContainer getAttack(int i) {
        return moveSet.get(i);
    }


    public String getName() {
        return name;
    }
    public ArrayList<Attack> getMoveSet(){return moveSet;}

    @Override
    public int getId() {return this.id; }

    @Override
    public int getHealth() {
        return this.currentHealth;
    }

    @Override
    public void setHealth(int health){this.currentHealth = health;}

    @Override
    public void lockHealth() {
        this.lockHealth = true;
    }

    @Override
    public int getSpeed() {
        return currentSpeed;
    }

    @Override
    public void lockSpeed() {
        this.lockSpeed = true;
    }

    @Override
    public void modifySpeed(int buffFactor) {
        this.speedBuffFactor += buffFactor;
        alterCurrentStats();
    }


    @Override
    public int getAttackPower() {
        return currentAttackPower;
    }

    @Override
    public void lockAttackPower() {
        this.lockAttackPower = true;
    }

    @Override
    public void modifyAttackPower(int buffFactor) {
        this.attackPowerBuffFactor += buffFactor;
        alterCurrentStats();
    }

    @Override
    public int getDefence() {
        return currentDefence;
    }

    @Override
    public void lockDefence() {
        this.lockDefence = true;
    }

    @Override
    public void modifyDefence(int buffFactor) {
        this.defenceBuffFactor += buffFactor;
        alterCurrentStats();
    }

    @Override
    public PTypes getType1() {
        return this.type1;
    }

    @Override
    public PTypes getType2() {
        return this.type2;
    }

    @Override
    public int getLevel() {
        return level;
    }
}
