package model.entities;

import model.PTypes;
import model.attack.Attack;
import model.attack.AttackFactory;
import model.effects.IEffectContainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Puckemon implements IPuckemon {

    protected int id;
    protected String name;
    protected List<PTypes> types;

    protected int baseHealth;
    protected int baseAttackPower;
    protected int baseDefence;
    protected int baseSpeed;

    protected int level;
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

    protected List<String> moveList;
    protected ArrayList<Attack> moveSet = new ArrayList<>(4);

    public Puckemon(int id, int level, String name, List<PTypes> types, int baseHealth, int baseAttackPower, int baseDefence, int baseSpeed, List<String> moveList){
        this.id = id;
        this.level = level;
        this.name = name;
        this.types = types;
        this.baseHealth = baseHealth;
        this.baseAttackPower = baseAttackPower;
        this.baseDefence = baseDefence;
        this.baseSpeed = baseSpeed;
        this.moveList = moveList;

        calculateLevelStats();
        alterCurrentStats();
        this.currentHealth = this.maxHealth;
        this.currentDefence = this.defence;
        fillMoveSet();
    }

    protected void fillMoveSet(){
        if (moveList.size() <= 4){
            for (int i = 0; i < moveList.size(); i++){
                moveSet.add(AttackFactory.createByName(moveList.get(i)));
            }
        } else{
            ArrayList<String> randList = new ArrayList<>(moveList);
            Collections.shuffle(randList);
            for (int i = 0; i < 4; i++) {
                moveSet.add(AttackFactory.createByName(randList.get(i)));
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
        this.maxHealth = (2*baseHealth*level)/100 + level + 10;
        this.attackPower = (2*baseAttackPower*level)/100+5;
        this.defence = (2*baseDefence*level)/100+5;
        this.speed = (2*baseSpeed*level)/100+5;
    }

    protected void alterCurrentStats(){
        if (attackPowerBuffFactor < 0){
            currentAttackPower = (attackPower) * (2 / (2 + (-1) * attackPowerBuffFactor));
        }else{
            currentAttackPower = (int) (attackPower * (1 + attackPowerBuffFactor * 0.25));
        }
        if (defenceBuffFactor < 0){
            currentDefence = (defence) * (2 / (2 + (-1) * defenceBuffFactor));
        }else{
            currentDefence = (int) (defence * (1 + defenceBuffFactor * 0.25));
        }
        if (speedBuffFactor < 0){
            currentSpeed =  (speed) * (2 / (2 + (-1) * speedBuffFactor));
        }else{
            currentSpeed = (int) (speed * (1 + speedBuffFactor * 0.25));
        }
    }

    public Attack getAttack(int i) {
        return moveSet.get(i);
    }


    public List<Attack> getMoveSet(){return moveSet;}

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getId() {return this.id; }

    @Override
    public int getHealth() {
        return this.currentHealth;
    }

    @Override
    public int getMaxHealth(){
        return this.maxHealth;
    }

    @Override
    public void doDamage(int damage){
        if(damage > 0){
            if(damage > this.currentHealth) this.currentHealth = 0;
            else this.currentHealth -= damage;
        }
    }

    @Override
    public void heal(int heal){
        if(heal>0){
            if(heal+this.currentHealth > this.maxHealth) this.currentHealth = this.maxHealth;
            else this.currentHealth += heal;
        }
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
    public List<PTypes> getTypes() {
        return this.types;
    }

    @Override
    public int getLevel() {
        return level;
    }
}
