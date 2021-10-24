package model.effects.effectTypes;

import static org.junit.Assert.*;

import model.PTypes;
import services.puckemonGenerator.CreatePuckemon;
import model.puckemon.IPuckemon;
import org.junit.Test;

public class TestDoDamage {

    @Test
    public void testDoDamage() {
        CreatePuckemon createPuckemon = new CreatePuckemon();
        IPuckemon p = createPuckemon.createOwnedPuckemon(1,1);
        IPuckemon enemyP = createPuckemon.createOwnedPuckemon(1,1);

        DoDamage damage = new DoDamage(2, PTypes.NORMAL);
        damage.execute(p, enemyP);

        //Because damage has a random factor the check involves a range
        assertTrue((enemyP.getHealth() <= 10) && (enemyP.getHealth() >= 8));
    }

}
