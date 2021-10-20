package model.entities;

import static org.junit.Assert.*;

import model.GameBuilder;
import org.junit.Test;

public class TestPuckeTrainer {

    @Test
    public void testCheckIfDefeated() {
        GameBuilder gameBuilder = new GameBuilder();
        IFighter trainer = new PuckeTrainer("El bertil", gameBuilder.getRandOpponentTeam(1,5), false);

        IPuckemon activePuckemon = trainer.getActivePuckemon();
        activePuckemon.setHealth(0);

        boolean isDefeated = trainer.checkIfDefeated();
        boolean expected = true;

        assertEquals(expected, isDefeated);  // The logical check
    }

    @Test
    public void testCheckIfNotDefeated() {
        GameBuilder gameBuilder = new GameBuilder();
        IFighter trainer = new PuckeTrainer("El bertil", gameBuilder.getRandOpponentTeam(1,5), false);

        boolean isDefeated = trainer.checkIfDefeated();
        boolean expected = false;

        assertEquals(expected, isDefeated);  // The logical check
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

        assertEquals(expected, isDefeated);  // The logical check
    }

}
