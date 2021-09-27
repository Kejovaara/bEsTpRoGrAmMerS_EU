package model.entities;

import model.PTypes;

public interface IPuckemon {

    public void setHealth(int health);
    public int getHealth();
    public void lockHealth(boolean locked);

    public void setSpeed(int speed);
    public int getSpeed();
    public void lockSpeed(boolean locked);
    public void modifySpeed(int buffFactor);


    public void setAttackPower(int attackPower);
    public int getAttackPower();
    public void lockAttackPower(boolean locked);
    public void modifyAttackPower(int buffFactor);

    public void setDefence(int defence);
    public int getDefence();
    public void lockDefence(boolean locked);
    public void modifyDefence(int buffFactor);


    public PTypes getType();
    public int getLevel();
}

