package model;

import model.attack.Attack;
import model.attack.AttackBuilder;
import services.puckemonGenerator.CreatePuckemon;
import model.puckemon.IPuckemon;
import model.puckemon.Puckemon;
import model.inventories.Item;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TestModel {

    @Test
    public void testStartCombatTrainer() {
        Model model = new Model();

        model.startCombatTrainer(1,10, false);

        model.useFlee();

        String outcome = model.getBattleOutcome();

        assertEquals("Ongoing", outcome);
    }

    @Test
    public void testStartCombatWildPuckemon() {
        Model model = new Model();

        model.startCombatWildPuckemon(1);

        String outcome = model.getBattleOutcome();

        assertEquals("Ongoing", outcome);

        model.useFlee();

        outcome = model.getBattleOutcome();

        assertEquals("Defeat", outcome);
    }

    @Test
    public void testGetAttack() {
        Model model = new Model();

        Attack attack = model.getAttack(0);
        Attack expectedAttack = AttackBuilder.createByName("Tackle");

        assertEquals(expectedAttack.getName(), attack.getName());
    }

    @Test
    public void testGetPlayerPuckemon() {
        Model model = new Model();
        CreatePuckemon createPuckemon = new CreatePuckemon();

        Puckemon puckemon = model.getActivePlayerPuckemon();
        Puckemon expectedPuckemon = createPuckemon.createOwnedPuckemon(1, 1);

        assertEquals(expectedPuckemon.getName(), puckemon.getName());
    }

    @Test
    public void testGetOpponentPuckemon() {
        Model model = new Model();

        model.startCombatWildPuckemon(10);

        IPuckemon puckemon = model.getOpponentPuckemon();

        assertTrue(puckemon.getLevel() >= 10);
    }

    @Test
    public void testUseAttack() {
        Model model = new Model();

        model.startCombatTrainer(1, 1, true);
        model.useAttack(0);

        IPuckemon puckemon = model.getOpponentPuckemon();

        assertTrue(puckemon.getHealth() < puckemon.getMaxHealth());
    }

    @Test
    public void testUseItem() {
        Model model = new Model();

        List<Item> inv = model.getInventory();
        Item item = inv.get(0);
        int quantity = item.getQuantity();
        int preLength = inv.size();

        for (int i = 0; i < quantity ; i++) {
            model.useItem(0);
        }

        assertTrue( model.getInventory().size() < preLength );
    }

    @Test
    public void testGetParty() {
        Model model = new Model();

        assertEquals(5, model.getParty().size());
    }

    @Test
    public void testGetAttacks() {
        Model model = new Model();

        assertEquals(model.getAttacks().get(0).getName(), "Tackle");
        assertEquals(model.getAttacks().get(1).getName(), "Swords dance");
    }

    @Test
    public void testSwitchPuckemon() {
        Model model = new Model();
        Puckemon pre = model.getActivePlayerPuckemon();

        int index = 1;
        Puckemon switchedTo = model.getParty().get(index);
        model.switchPuckemon(index);
        Puckemon post = model.getActivePlayerPuckemon();

        assertNotEquals(pre, post);
        assertEquals(post, switchedTo);
    }
}
