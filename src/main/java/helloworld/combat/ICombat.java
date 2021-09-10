package helloworld.combat;

import helloworld.entities.Puckemon;

public interface ICombat {

    public Attack getAttack();
    public void makeAttack(Attack attack);
    public Puckemon getActivePuckemon();
}
