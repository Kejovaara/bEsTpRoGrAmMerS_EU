package model.effects;

import model.entities.IPuckemon;

public interface IEffect {

    //kanske en metod för byta plats på IPokemon
    void execute(IPuckemon attackUser, IPuckemon opponent);
}
