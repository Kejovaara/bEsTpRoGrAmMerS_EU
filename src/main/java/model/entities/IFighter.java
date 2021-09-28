package model.entities;
import model.CombatOptions;
import model.effects.IEffectContainer;


public interface IFighter {
    public IEffectContainer getMoves();
    public CombatOptions getOptions();

}
