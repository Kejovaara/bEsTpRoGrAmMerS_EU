package model.entities;

import model.PTypes;

public interface IPuckemon {

    void setHealth(int health);
    int getHealth();
    void lockHealth(boolean locked);

    void setSpeed(int speed);
    int getSpeed();
    void lockSpeed(boolean locked);
    void modifySpeed(int buffFactor);

    void setAttackPower(int attackPower);
    int getAttackPower();
    void lockAttackPower(boolean locked);
    void modifyAttackPower(int buffFactor);

    void setDefence(int defence);
    int getDefence();
    void lockDefence(boolean locked);
    void modifyDefence(int buffFactor);

    PTypes getType();
    int getLevel();
}

