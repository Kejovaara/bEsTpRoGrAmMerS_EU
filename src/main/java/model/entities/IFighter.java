package model.entities;
import model.effects.IEffectContainer;

public interface IFighter {
    IEffectContainer makeMove(IPuckemon enemyP);
    IPuckemon getActivePuckemon();
    boolean checkIfDefeated();


}
