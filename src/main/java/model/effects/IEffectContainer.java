package model.effects;

import model.entities.IPuckemon;

import java.util.List;

public interface IEffectContainer {

    int getPriority();
    List<IEffect> getEffects();

//    public void hpSteal();
//    public void doDamage();
//    public void healYourselfAmount();
//    public void healYourselfPercentage();

    void execute(IPuckemon attackUser, IPuckemon opponent);


}
