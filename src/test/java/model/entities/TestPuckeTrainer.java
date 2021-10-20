package model.entities;

import static org.junit.Assert.*;

import model.GameBuilder;
import org.junit.Test;

import java.util.ArrayList;

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

}
