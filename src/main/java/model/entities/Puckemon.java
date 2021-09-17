package model.entities;

public class Puckemon {
    private int health = 100;
    private String name = "Puckemon";
    private int attackPowerBuffFactor = 0;
    private int baseAttackPower;
    private int baseDefence;
    private int baseSpeed;

    private int currentAttackPower;
    private int currentDefence;
    private int currentSpeed;

    private void changeCurrentAttackPower(){
        currentAttackPower = (int) (baseAttackPower * (1 + attackPowerBuffFactor * 0.25));
    }




    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }
}
