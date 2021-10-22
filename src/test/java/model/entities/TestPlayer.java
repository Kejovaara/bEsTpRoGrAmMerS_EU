package model.entities;

import model.PartyBuilder;
import model.entities.puckemon.FixedPuckemon;
import model.entities.puckemon.OwnedPuckemon;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestPlayer {

    @Test
    public void testCheckIfDefeated() {
        CreatePuckemon createPuckemon = new CreatePuckemon();
        OwnedPuckemon p = createPuckemon.createOwnedPuckemon(1,1);

        List<OwnedPuckemon> party = new ArrayList<>();
        party.add(p);
        Player trainer = new Player(party, 0);

        IPuckemon activePuckemon = trainer.getActivePuckemon();
        activePuckemon.doDamage(5000);

        boolean isDefeated = trainer.checkIfDefeated();

        assertTrue(isDefeated);
    }

    @Test
    public void testCheckIfNotDefeated() {
        CreatePuckemon createPuckemon = new CreatePuckemon();
        OwnedPuckemon p = createPuckemon.createOwnedPuckemon(1,1);

        List<OwnedPuckemon> party = new ArrayList<>();
        party.add(p);
        Player trainer = new Player(party, 0);

        boolean isDefeated = trainer.checkIfDefeated();

        assertFalse(isDefeated);
    }
}
