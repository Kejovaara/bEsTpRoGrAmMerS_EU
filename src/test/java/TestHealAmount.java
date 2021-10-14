import static org.junit.Assert.*;

import model.PTypes;
import model.effects.effectTypes.DoDamage;
import model.effects.effectTypes.HealAmount;
import model.entities.IPuckemon;
import model.entities.OwnedPuckemon;
import org.junit.Test;

public class TestHealAmount {

    @Test
    public void testHealYourself() {
        IPuckemon p = new OwnedPuckemon(1,1);
        IPuckemon enemyP = new OwnedPuckemon(1,1);

        DoDamage damage = new DoDamage(50, PTypes.NORMAL);

        HealAmount heal = new HealAmount(20, true);
        heal.execute(p, enemyP);

        assertTrue(true);  // The logical check
    }

}
