package model.effects.effectTypes;

import model.PTypes;
import model.effects.IEffect;
import model.entities.CreatePuckemon;
import model.entities.IPuckemon;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestGiveXP {

    @Test
    public void testGiveXP() {
        CreatePuckemon createPuckemon = new CreatePuckemon();
        IPuckemon p = createPuckemon.createOwnedPuckemon(1,1);
        IPuckemon enemyP = createPuckemon.createOwnedPuckemon(1,1);


        IEffect giveXP = new GiveXP(20);

        //Because damage has a random factor the check involves a range
        assertTrue(false);
    }
}
