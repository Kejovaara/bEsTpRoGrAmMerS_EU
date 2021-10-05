package model.entities;

import model.PTypes;

import java.util.List;

public interface IPuckemon {

    String getName();

    int getId();

    void setHealth(int health);
    int getHealth();
    void lockHealth(boolean locked);

    void setSpeed(int speed);
    int getSpeed();
    void lockSpeed(boolean locked);
    void modifySpeed(int buffFactor);


    int getAttackPower();
    void lockAttackPower(boolean locked);
    void modifyAttackPower(int buffFactor);

    void setDefence(int defence);
    int getDefence();
    void lockDefence(boolean locked);
    void modifyDefence(int buffFactor);


    PTypes getType1();
    PTypes getType2();
    int getLevel();
}

