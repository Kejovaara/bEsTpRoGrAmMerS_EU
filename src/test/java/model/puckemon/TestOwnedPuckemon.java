package model.puckemon;

import services.puckemonGenerator.CreatePuckemon;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestOwnedPuckemon {

    private CreatePuckemon createPuckemon = new CreatePuckemon();

    @Test
    public void testSetNickame() {
        OwnedPuckemon puckemon = createPuckemon.createOwnedPuckemonNickname(1,10,"Bamse");

        assertEquals("Bamse", puckemon.getNickName());  // The logical check
    }

    @Test
    public void testEvolve(){
        OwnedPuckemon puckemon = createPuckemon.createOwnedPuckemon(1,15);
        puckemon.giveExp(10000);
        assertEquals(true, puckemon.getEvolve());  // The logical check
    }

    @Test
    public void testLevelUp(){
        OwnedPuckemon puckemon = createPuckemon.createOwnedPuckemon(1,10);
        puckemon.giveExp(332);
        int expected = 11;

        assertEquals(expected, puckemon.getLevel());  // The logical check
    }

    @Test
    public void testLevelUpStats(){
        OwnedPuckemon puckemon = createPuckemon.createOwnedPuckemon(2,10);
        puckemon.giveExp(332);
        int expected = 18;

        assertEquals(expected, puckemon.getAttackPower());  // The logical check
    }

    @Test
    public void testLevelUpKeepHealthPercentage(){
        OwnedPuckemon puckemon = createPuckemon.createOwnedPuckemon(1,11);
        puckemon.doDamage(puckemon.getMaxHealth()/2);
        puckemon.giveExp(1729);
        double expected = puckemon.getMaxHealth()/2;
        double healthPercentage = puckemon.getHealth();

        assertEquals(expected,healthPercentage,1);  // The logical check
    }

    @Test
    public void testMaxExperience(){
        OwnedPuckemon puckemon = createPuckemon.createOwnedPuckemon(1,99);
        puckemon.giveExp(10000000);

        assertEquals(100,puckemon.getLevel());  // The logical check
    }

}
