package model.effects.effectTypes;

import services.puckemonGenerator.CreatePuckemon;
import model.puckemon.IPuckemon;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestDoDamagePercentage {

    @Test
    public void testDoDamagePercentage() {
        CreatePuckemon createPuckemon = new CreatePuckemon();
        IPuckemon p = createPuckemon.createOwnedPuckemon(1,1);
        IPuckemon enemyP = createPuckemon.createOwnedPuckemon(1,1);

        DoDamagePercentage damage = new DoDamagePercentage(50f);
        damage.execute(p, enemyP);

        //Check if 50% of hp remains
        assertTrue((enemyP.getHealth() == enemyP.getMaxHealth()/2));


        int hpBefore = enemyP.getHealth();
        DoDamagePercentage damage2 = new DoDamagePercentage(25f);
        damage2.execute(p, enemyP);

        //checks if 25% of 50% hp remains
        assertTrue(enemyP.getHealth() == (hpBefore-Math.round(hpBefore*(25/100f))));
    }
}
