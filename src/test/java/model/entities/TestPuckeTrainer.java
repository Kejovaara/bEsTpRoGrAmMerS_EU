package model.entities;

import static org.junit.Assert.*;

import model.GameBuilder;
import model.attack.Attack;
import model.attack.AttackFactory;
import model.effects.IEffect;
import model.effects.IEffectContainer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestPuckeTrainer {

    @Test
    public void testCheckIfDefeated() {
        GameBuilder gameBuilder = new GameBuilder();
        IFighter trainer = new PuckeTrainer("El bertil", gameBuilder.getRandOpponentTeam(1,5), false);

        IPuckemon activePuckemon = trainer.getActivePuckemon();
        activePuckemon.setHealth(0);

        boolean isDefeated = trainer.checkIfDefeated();
        boolean expected = true;

        assertEquals(expected, isDefeated);
    }

    @Test
    public void testCheckIfNotDefeated() {
        GameBuilder gameBuilder = new GameBuilder();
        IFighter trainer = new PuckeTrainer("El bertil", gameBuilder.getRandOpponentTeam(1,5), false);

        boolean isDefeated = trainer.checkIfDefeated();
        boolean expected = false;

        assertEquals(expected, isDefeated);
    }

    @Test
    public void testSwitchWhenFainted() {
        GameBuilder gameBuilder = new GameBuilder();
        IFighter trainer = new PuckeTrainer("El bertil", gameBuilder.getRandOpponentTeam(2,5), false);

        IPuckemon activePuckemon = trainer.getActivePuckemon();
        activePuckemon.setHealth(0);

        //Opponent should switch
        boolean isDefeated = trainer.checkIfDefeated();
        boolean expected = false;

        assertEquals(expected, isDefeated);
    }

    @Test
    public void testMakeMoveWithActive() {
        CreatePuckemon createPuckemon = new CreatePuckemon();
        IPuckemon enemyP = createPuckemon.createOwnedPuckemon(1,1);
        VildPuckemon p = createPuckemon.createVildPuckemon(1,1);

        ArrayList<VildPuckemon> party = new ArrayList<>();
        party.add(p);
        IFighter trainer = new PuckeTrainer("El bertil", party, true);

        Attack expectedAttack = AttackFactory.createByName("Tackle");
        Attack attack = (Attack)trainer.makeMove(enemyP);

        String name = attack.getName();
        String expected = expectedAttack.getName();

        assertEquals(expected, name);
    }

}
