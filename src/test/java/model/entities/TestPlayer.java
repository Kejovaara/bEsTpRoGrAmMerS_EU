package model.entities;

import model.entities.puckemon.OwnedPuckemon;
import model.entities.puckemon.Puckemon;
import model.inventories.Inventory;
import model.inventories.Item;
import model.inventories.ItemBuilder;
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

    @Test
    public void testGetItem() {
        CreatePuckemon createPuckemon = new CreatePuckemon();
        OwnedPuckemon p = createPuckemon.createOwnedPuckemon(1,1);

        List<OwnedPuckemon> party = new ArrayList<>();
        party.add(p);

        Item item = ItemBuilder.getRandom();
        List<Item> items = new ArrayList<>();
        items.add(item);
        Inventory inventory = new Inventory(items);

        Player player = new Player(party, inventory, 0);

        assertEquals(player.getItem(0), item);
    }

    @Test
    public void testAddItem() {
        CreatePuckemon createPuckemon = new CreatePuckemon();
        OwnedPuckemon p = createPuckemon.createOwnedPuckemon(1,1);

        List<OwnedPuckemon> party = new ArrayList<>();
        party.add(p);

        Item item = ItemBuilder.getRandom();

        Player player = new Player(party, 0);

        player.addItem(item);

        assertEquals(player.getItem(0), item);
    }
}
