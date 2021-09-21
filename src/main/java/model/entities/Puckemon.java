package model.entities;

public class Puckemon {

    private String name = "Puckemon";
    private String nickName;
//    private Type type1;
//    private Type type2;

    private int baseHealth;
    private int baseAttackPower;
    private int baseDefence;
    private int baseSpeed;

    private int evolutionLevel;
    private int evolutionID;

    // -------------

    /**
     * These are a pokemons stats. They are solely based on the baseStats and current level.
     */
    private int health;
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
    }

    private void calculateLevelStats(){
        this.health = (2*baseHealth+level)/100 + level + 10;
        this.attackPower = (2*baseAttackPower+level)/100+5;
        this.defence = (2*baseDefence+level)/100+5;
        this.speed = (2*baseSpeed+level)/100+5;
    }

    private void checkLevelUp(){

    }

    private void changeCurrentAttackPower(){
        if (attackPowerBuffFactor < 0){
            currentAttackPower = (int) (attackPower) * (2 / (2 + (-1) * attackPowerBuffFactor));
        }else{
            currentAttackPower = (int) (attackPower * (1 + attackPowerBuffFactor * 0.25));
        }
    }




    public int getHealth() {
        return baseHealth;
    }

    public String getName() {
        return name;
    }
}
