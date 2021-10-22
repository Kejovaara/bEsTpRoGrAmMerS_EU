package model.entities;

import model.PTypes;
import model.attack.Attack;
import model.effects.IEffectContainer;

import java.util.List;

public interface IPuckemon {

    String getName();

    int getId();

    void doDamage(int damage);
    void heal(int heal);
    int getHealth();
    int getMaxHealth();
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

    List<Attack> getMoveSet();
    IEffectContainer getAttack(int i);
    List<PTypes> getTypes();
    int getLevel();
}

