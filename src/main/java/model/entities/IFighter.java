package model.entities;

import model.combat.Attack;

public interface IFighter {

    public Attack getAttack();
    public void makeAttack(Attack attack);
    public OwnedPuckemon getActivePuckemon();
}
