package model.entities;
import model.effects.IEffectContainer;

public interface IFighter {
    public IEffectContainer makeMove();
    public IPuckemon getActivePuckemon();


}
