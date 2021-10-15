//package model;
//
//import static org.junit.Assert.*;
//
//import model.MonRegisterInterpreter;
//import model.PTypes;
//import org.junit.Test;
//
//import java.util.ArrayList;
//
//public class TestMonRegisterInterpreter {
//
//    private MonRegisterInterpreter monRegisterInterpreter = new MonRegisterInterpreter();
//
//    @Test
//    public void testGetName() {
//        int id = 3;
//        String expected = "Venusaur";
//
//        String name = monRegisterInterpreter.getName(id);
//
//        assertEquals(name, expected);  // The logical check
//    }
//
//    @Test
//    public void testGetType() {
//        int id = 3;
//        PTypes expectedType1 = PTypes.GRASS;
//        PTypes expectedType2 = PTypes.POISON;
//
//        PTypes type1 = monRegisterInterpreter.getType1(id);
//        PTypes type2 = monRegisterInterpreter.getType2(id);
//
//        assertEquals(type1, expectedType1);  // The logical check
//        assertEquals(type2, expectedType2);  // The logical check
//    }
//
//    @Test
//    public void testGetBaseHealth() {
//        int id = 3;
//        int expected = 80;
//        int baseHealth = monRegisterInterpreter.getBaseHealth(id);
//
//        assertEquals(baseHealth, expected);  // The logical check
//    }
//
//    @Test
//    public void testGetBaseAttack() {
//        int id = 3;
//        int expected = 82;
//        int baseAttack = monRegisterInterpreter.getBaseAttack(id);
//
//        assertEquals(baseAttack, expected);  // The logical check
//    }
//
//    @Test
//    public void testGetBaseDefence() {
//        int id = 3;
//        int expected = 83;
//        int baseDefence = monRegisterInterpreter.getBaseDefence(id);
//
//        assertEquals(baseDefence, expected);  // The logical check
//    }
//
//    @Test
//    public void testGetBaseSpeed() {
//        int id = 3;
//        int expected = 80;
//        int baseSpeed = monRegisterInterpreter.getBaseSpeed(id);
//
//        assertEquals(baseSpeed, expected);  // The logical check
//    }
//
//    @Test
//    public void testGetEvolutionLevel() {
//        int id = 3;
//        int expected = 101;
//        int evolutionLevel = monRegisterInterpreter.getEvolutionLevel(id);
//
//        assertEquals(evolutionLevel, expected);  // The logical check
//    }
//
//    @Test
//    public void testGetEvolutionId() {
//        int id = 3;
//        int expected = 0;
//        int evolutionId = monRegisterInterpreter.getEvolutionId(id);
//
//        assertEquals(evolutionId, expected);  // The logical check
//    }
//
//    @Test
//    public void getMoveList() {
//        int id = 3;
//        ArrayList<String> expected = new ArrayList<>();
//
//        expected.add("Tackle");
//        expected.add("SwordsDance");
//        expected.add("Absorb");
//
//        ArrayList<String> moveList = monRegisterInterpreter.getMoveList(id);
//
//        assertEquals(moveList, expected);  // The logical check
//    }
//
//}
