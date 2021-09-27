package model.entities;

import model.attack.Attack;

public interface IFighter {

    public Attack getAttack();
    public void makeAttack(Attack attack);
    public Puckemon getActivePuckemon();
}
