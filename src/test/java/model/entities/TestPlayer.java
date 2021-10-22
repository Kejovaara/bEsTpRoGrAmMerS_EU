package model.entities;

import model.PartyBuilder;
import model.entities.puckemon.FixedPuckemon;
import model.entities.puckemon.OwnedPuckemon;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

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
        boolean expected = true;

        assertEquals(expected, isDefeated);
    }
}
