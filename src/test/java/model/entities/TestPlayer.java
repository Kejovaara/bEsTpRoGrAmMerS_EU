package model.entities;

import model.PartyBuilder;
import model.entities.puckemon.FixedPuckemon;
import model.entities.puckemon.OwnedPuckemon;
import model.entities.puckemon.Puckemon;
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
        Player player = new Player(party, 0);

        IPuckemon activePuckemon = player.getActivePuckemon();
        activePuckemon.doDamage(5000);

        boolean isDefeated = player.checkIfDefeated();

        assertTrue(isDefeated);
    }

    @Test
    public void testCheckIfNotDefeated() {
        CreatePuckemon createPuckemon = new CreatePuckemon();
        OwnedPuckemon p = createPuckemon.createOwnedPuckemon(1,1);

        List<OwnedPuckemon> party = new ArrayList<>();
        party.add(p);
        Player player = new Player(party, 0);

        boolean isDefeated = player.checkIfDefeated();

        assertFalse(isDefeated);
    }

    @Test
    public void testSwitch() {
        CreatePuckemon createPuckemon = new CreatePuckemon();
        OwnedPuckemon p = createPuckemon.createOwnedPuckemon(1,1);
        OwnedPuckemon p1 = createPuckemon.createOwnedPuckemon(1,1);

        List<OwnedPuckemon> party = new ArrayList<>();
        party.add(p);
        party.add(p1);
        Player player = new Player(party, 0);

        player.switchPuckemon(1);
        Puckemon puckemon = player.getActivePuckemon();

        assertEquals(puckemon, p1);
    }
}
