package helloworld.combat;

public interface ICombat {

    public Attack attack();
    public void reduceHealth();
    public Puckemon getActivePuckemon();
}
