package model.effects.effectTypes;

import services.puckemonGenerator.CreatePuckemon;
import model.puckemon.IPuckemon;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestModifyAttackPower {

    @Test
    public void testModifyAttackPowerSelf() {
        CreatePuckemon createPuckemon = new CreatePuckemon();
        IPuckemon p = createPuckemon.createOwnedPuckemon(1,1);
        IPuckemon enemyP = createPuckemon.createOwnedPuckemon(1,1);
        int previousPower = p.getAttackPower();
        int buffFactor = 2;

        ModifyAttackPower modifyAttackPower = new ModifyAttackPower(buffFactor, false);
        modifyAttackPower.execute(p, enemyP);

        int expected = previousPower + buffFactor;

        assertEquals(p.getAttackPower(), expected);
    }

    @Test
    public void testModifyAttackPowerOpponent() {
        CreatePuckemon createPuckemon = new CreatePuckemon();
        IPuckemon p = createPuckemon.createOwnedPuckemon(1,1);
        IPuckemon enemyP = createPuckemon.createOwnedPuckemon(1,1);
        int previousPower = enemyP.getAttackPower();
        int buffFactor = 2;

        ModifyAttackPower modifyAttackPower = new ModifyAttackPower(buffFactor, true);
        modifyAttackPower.execute(p, enemyP);

        int expected = previousPower + buffFactor;

        assertEquals(enemyP.getAttackPower(), expected);
    }

}
