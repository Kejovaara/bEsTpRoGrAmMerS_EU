package model.effects.effectTypes;

import static org.junit.Assert.*;

import model.PTypes;
import model.entities.IPuckemon;
import model.entities.OwnedPuckemon;
import org.junit.Test;

public class TestDoDamage {

    @Test
    public void testDoDamage() {
        IPuckemon p = new OwnedPuckemon(1,1);
        IPuckemon enemyP = new OwnedPuckemon(1,1);

        DoDamage damage = new DoDamage(2, PTypes.NORMAL);
        System.out.println(enemyP.getHealth());

        damage.execute(p, enemyP);

        //Because damage has a random factor the check involves a range
        assertTrue((enemyP.getHealth() <= 10) && (enemyP.getHealth() >= 8));
    }

}
