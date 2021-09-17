package model.effects;

import java.util.List;

public interface IEffectContainer {

    public int getPriority();
    public List<IEffect> getEffects();

    public void hpSteal();
    public void doDamage();
    public void healYourselfAmount();
    public void healYourselfPercentage();



}
