package model.entities;

import model.PTypes;

import java.util.List;

public interface IPuckemon {

    String getName();

    int getId();

    void setHealth(int health);
    int getHealth();
    void lockHealth();

    int getSpeed();
    void lockSpeed();
    void modifySpeed(int buffFactor);


    int getAttackPower();
    void lockAttackPower();
    void modifyAttackPower(int buffFactor);

    int getDefence();
    void lockDefence();
    void modifyDefence(int buffFactor);


    PTypes getType1();
    PTypes getType2();
    int getLevel();
}

