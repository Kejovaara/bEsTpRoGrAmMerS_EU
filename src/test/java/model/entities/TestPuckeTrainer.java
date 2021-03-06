package model.entities;

import static org.junit.Assert.*;

import model.PartyBuilder;
import model.attack.Attack;
import model.attack.AttackBuilder;
import model.effects.IEffectContainer;
import model.puckemon.FixedPuckemon;
import model.puckemon.IPuckemon;
import model.inventories.Inventory;
import model.inventories.Item;
import model.inventories.ItemBuilder;
import org.junit.Test;
import serviceControllers.puckemonGenerator.CreatePuckemon;

import java.util.ArrayList;
import java.util.List;

public class TestPuckeTrainer {

    @Test
    public void testCheckIfDefeated() {
        PartyBuilder partyBuilder = new PartyBuilder();
        IFighter trainer = new PuckeTrainer("El bertil", partyBuilder.getRandOpponentTeam(1,5), false);

        IPuckemon activePuckemon = trainer.getActivePuckemon();
        activePuckemon.doDamage(5000);

        boolean isDefeated = trainer.checkIfDefeated();

        assertTrue(isDefeated);
    }

    @Test
    public void testCheckIfNotDefeated() {
        PartyBuilder partyBuilder = new PartyBuilder();
        IFighter trainer = new PuckeTrainer("El bertil", partyBuilder.getRandOpponentTeam(1,5), false);

        boolean isDefeated = trainer.checkIfDefeated();

        assertFalse(isDefeated);
    }

    @Test
    public void testSwitchWhenFainted() {
        PartyBuilder partyBuilder = new PartyBuilder();
        IFighter trainer = new PuckeTrainer("El bertil", partyBuilder.getRandOpponentTeam(2,5), false);

        IPuckemon activePuckemon = trainer.getActivePuckemon();
        activePuckemon.doDamage(5000);

        //Opponent should switch
        boolean isDefeated = trainer.checkIfDefeated();

        assertFalse(isDefeated);
    }

    @Test
    public void testMakeMoveWithActive() {
        CreatePuckemon createPuckemon = new CreatePuckemon();
        IPuckemon enemyP = createPuckemon.createOwnedPuckemon(1,1);
        FixedPuckemon p = createPuckemon.createFixedPuckemon(1,1);

        List<FixedPuckemon> party = new ArrayList<>();
        party.add(p);
        IFighter trainer = new PuckeTrainer("El bertil", party, true);

        Attack expectedAttack = AttackBuilder.createByName("Tackle");
        Attack attack = (Attack)trainer.makeMove(enemyP);

        String name = attack.getName();
        String expected = expectedAttack.getName();

        assertEquals(expected, name);
    }

    @Test
    public void testMakeMoveSwitch() {
        CreatePuckemon createPuckemon = new CreatePuckemon();
        IPuckemon enemyP = createPuckemon.createOwnedPuckemon(1,1);
        FixedPuckemon p = createPuckemon.createFixedPuckemon(1,1);
        //Create strong Puckemon that is worth switching to
        FixedPuckemon strongP = createPuckemon.createFixedPuckemon(5,1);

        List<FixedPuckemon> party = new ArrayList<>();
        party.add(p);
        party.add(strongP);
        IFighter trainer = new PuckeTrainer("El bertil", party, true);

        IEffectContainer attack = trainer.makeMove(enemyP);

        IPuckemon activePuckemon = trainer.getActivePuckemon();

        //Check that no IEffectContainer has been returned
        assertNull(attack);

        //Check that switch has occurred
        assertEquals(activePuckemon, strongP);
    }

    @Test
    public void testGetItem() {
        Item item = ItemBuilder.getRandom();
        List<Item> items = new ArrayList<>();
        items.add(item);
        Inventory inventory = new Inventory(items);

        PartyBuilder partyBuilder = new PartyBuilder();
        //Second constructor of PuckeTrainer
        PuckeTrainer trainer = new PuckeTrainer("El bertil",  partyBuilder.getRandOpponentTeam(1,5), inventory, false);


        //Check that no IEffectContainer has been returned
        assertEquals(trainer.getItem(0),item);
    }

    @Test
    public void testAddItem() {
        Item item = ItemBuilder.getRandom();

        PartyBuilder partyBuilder = new PartyBuilder();
        PuckeTrainer trainer = new PuckeTrainer("El bertil",  partyBuilder.getRandOpponentTeam(1,5), false);

        trainer.addItem(item);

        //Check that no IEffectContainer has been returned
        assertEquals(trainer.getItem(0),item);
    }

}
