package model.effects;

import model.entities.IPuckemon;

import java.util.List;

public interface IEffectContainer {

    int getPriority();
    List<IEffect> getEffects();

    void execute(IPuckemon attackUser, IPuckemon opponent);
}
