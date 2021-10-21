package model.entities;

import model.PTypes;
import model.attack.Attack;
import model.attack.AttackFactory;
import model.attack.AttackBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A puckemon fights during combat. It has its personal characteristics, but every puckemon has/need the following data.
 * @author Lukas Jigberg
 */
public abstract class Puckemon implements IPuckemon {

    protected int id;
    protected String name;

    /**
     * A list of 1-2 puckemon types.
     */
    protected List<PTypes> types;

    /**
     * These are specific for the Puckemon and remain the same.
     */
    protected int baseHealth;
    protected int baseAttackPower;
    protected int baseDefence;
    protected int baseSpeed;
    protected int level;


    /**
     * These are a puckemons stats. They are solely based on the baseStats and current level.
     * @see "calculateLevelStat"
     */
    protected int maxHealth;
    protected int attackPower;
    protected int defence;
    protected int speed;

    /**
     * These are a puckemons stats during the current combat. They can be and are supposed to be altered.
     * @see "alterCurrentStats"
     */
    protected int currentHealth;
    protected int currentAttackPower;
    protected int currentDefence;
    protected int currentSpeed;

    /**
     * BuffFactor are used to alter the CurrentStats. They can be changed by the effects of attacks.
     */
    protected int attackPowerBuffFactor = 0;
    protected int defenceBuffFactor = 0;
    protected int speedBuffFactor = 0;

    /**
     * Future update/sprint. Should lock the stats from changing during combat.
     */
    protected boolean lockHealth = false;
    protected boolean lockAttackPower = false;
    protected boolean lockDefence = false;
    protected boolean lockSpeed = false;

    /**
     * <br> moveList is a list of strings with all the attacks a puckemon can use.
     * <br> moveSet is the 1-4 attacks a Puckemon can use during combat.
     */
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
        this.currentHealth = this.maxHealth;
        fillMoveSet();
    }

    /**
     * Fills moveSet with moves from moveList. If there is more than 4 moves in list it chooses 4 at random.
     */
    protected void fillMoveSet(){
        if (moveList.size() <= 4){
            for (String s : moveList) {
                moveSet.add(AttackBuilder.createByName(s));
            }
        } else{
            ArrayList<String> randList = new ArrayList<>(moveList);
            Collections.shuffle(randList);
            for (int i = 0; i < 4; i++) {
                moveSet.add(AttackBuilder.createByName(randList.get(i)));
            }
        }
    }

    /**
     * Future update/sprint unlocks stats after locking them.
     */
    protected void unlockStats(){
        this.lockHealth = false;
        this.lockAttackPower = false;
        this.lockDefence = false;
        this.lockSpeed = false;
    }

    /**
     * Calculates the level stats depending on level. It then alters currentStats.
     */
    protected void calculateLevelStats(){
        this.maxHealth = (2*baseHealth*level)/100 + level + 10;
        this.attackPower = (2*baseAttackPower*level)/100+5;
        this.defence = (2*baseDefence*level)/100+5;
        this.speed = (2*baseSpeed*level)/100+5;
        alterCurrentStats();
    }

    /**
     * Sets the current attack/defence/speed depending on that stats buffFactor.
     * +2 buff will result in 1.5 * currentAttack. -2 buff will result in 2/4 * currentAttack
     */
    protected void alterCurrentStats(){
        if (attackPowerBuffFactor < 0){
            currentAttackPower = (int)(((double)attackPower) * (2/(2 +(-1)*(double)attackPowerBuffFactor)));
        }else{
            currentAttackPower = (int) (attackPower * (1 + attackPowerBuffFactor * 0.25));
        }
        if (defenceBuffFactor < 0){
            currentDefence = (int)(((double)defence) * (2/(2 +(-1)*(double)defenceBuffFactor)));
        }else{
            currentDefence = (int) (defence * (1 + defenceBuffFactor * 0.25));
        }
        if (speedBuffFactor < 0){
            currentSpeed =  (int)(((double)speed) * (2/(2 +(-1)*(double)speedBuffFactor)));
        }else{
            currentSpeed = (int) (speed * (1 + speedBuffFactor * 0.25));
        }
    }

    /**
     * Resets the modifications done to a Puckemon when defeated, switched or when battle is over.
     */
    public void resetStats(){
        attackPowerBuffFactor = 0;
        defenceBuffFactor = 0;
        speedBuffFactor = 0;
        alterCurrentStats();
    }

    public Attack getAttack(int i) {
        return moveSet.get(i);
    }
    public List<String> getMoveList(){return moveList;}

    @Override
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
