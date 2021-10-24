package model.puckemon;

import static org.junit.Assert.*;

import model.PTypes;
import serviceControllers.puckemonGenerator.CreatePuckemon;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestCreatePuckemon {

    private CreatePuckemon createPuckemon = new CreatePuckemon();
    OwnedPuckemon puckemon = createPuckemon.createOwnedPuckemon(2,1);

    @Test
    public void testGetName() {
        String expected = "Ivysaur";

        String name = puckemon.getName();

        assertEquals(name, expected);  // The logical check
    }

    @Test
    public void testGetType() {
        PTypes expectedType1 = PTypes.GRASS;
        PTypes expectedType2 = PTypes.POISON;

        PTypes type1 = puckemon.getTypes().get(0);
        PTypes type2 = puckemon.getTypes().get(puckemon.getTypes().size()-1);

        assertEquals(type1, expectedType1);  // The logical check
        assertEquals(type2, expectedType2);  // The logical check
    }

    @Test
    public void testGetBaseHealth() {
        int expected = 12;
        int health = puckemon.getHealth();

        assertEquals(health, expected);  // The logical check
    }

    @Test
    public void testGetBaseAttack() {
        int expected = 6;
        int baseAttack = puckemon.getAttackPower();

        assertEquals(baseAttack, expected);  // The logical check
    }

    @Test
    public void testGetBaseDefence() {
        int expected = 6;
        int baseDefence = puckemon.getDefence();

        assertEquals(baseDefence, expected);  // The logical check
    }

    @Test
    public void testGetBaseSpeed() {
        int expected = 6;
        int baseSpeed = puckemon.getSpeed();

        assertEquals(baseSpeed, expected);  // The logical check
    }

    @Test
    public void testGetEvolutionLevel() {
        int expected = 36;
        int evolutionLevel = puckemon.getEvolutionLevel();

        assertEquals(evolutionLevel, expected);  // The logical check
    }

    @Test
    public void testGetEvolutionId() {
        int expected = 3;
        int evolutionId = puckemon.getEvolutionId();

        assertEquals(evolutionId, expected);  // The logical check
    }

    @Test
    public void getMoveList() {
        List<String> expected = new ArrayList<>();

        expected.add("Tackle");
        expected.add("SwordsDance");

        List<String> moveList = puckemon.getMoveList();

        assertEquals(moveList, expected);  // The logical check
    }

}
