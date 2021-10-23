package model.effects.effectTypes;

import static org.junit.Assert.*;

import model.PTypes;
import services.puckemonGenerator.CreatePuckemon;
import model.entities.IPuckemon;
import org.junit.Test;

public class TestHpSteal {

    @Test
    public void testHpSteal() {
        CreatePuckemon createPuckemon = new CreatePuckemon();
        IPuckemon p = createPuckemon.createOwnedPuckemon(1,1);
        IPuckemon enemyP = createPuckemon.createOwnedPuckemon(1,1);
        p.doDamage(5000);

        int enemyPreHealth = enemyP.getHealth();
        int preHealth = p.getHealth();
        HpSteal steal = new HpSteal(2, PTypes.NORMAL,0.5f);
        steal.execute(p, enemyP);

        int damage = enemyPreHealth - enemyP.getHealth();

        int healAmount = (int)Math.ceil((float)damage/2);
        int expected = preHealth + healAmount;
        System.out.println(expected);

        //Check damage, because damage has a random factor the check involves a range
        assertTrue((enemyP.getHealth() == (enemyPreHealth - damage)));

        //Check heal, because damage (and therefore heal in this instance) has a random factor the check involves a range
        assertEquals(p.getHealth(), expected);
    }

}
