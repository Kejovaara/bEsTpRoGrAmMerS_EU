package model.effects.effectTypes;

import static org.junit.Assert.*;

import model.PTypes;
import model.entities.IPuckemon;
import model.entities.OwnedPuckemon;
import org.junit.Test;

public class TestHealPercentage {

    @Test
    public void testHealYourself() {
        IPuckemon p = new OwnedPuckemon(1,1);
        IPuckemon enemyP = new OwnedPuckemon(1,1);

        DoDamage damage = new DoDamage(50, PTypes.NORMAL);
        damage.execute(enemyP, p);
        int health = p.getHealth();
        int amount = 1;
        int expected = Math.min((health + (health * amount)), p.getMaxHealth());

        HealPercentage heal = new HealPercentage(amount, true);
        heal.execute(p, enemyP);

        System.out.println(expected);

        assertEquals(p.getHealth(), expected);  // The logical check
    }


}
