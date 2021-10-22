package model.effects.effectTypes;

import model.PTypes;
import model.effects.IEffect;
import model.entities.CreatePuckemon;
import model.entities.IPuckemon;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestRestorePP {
    @Test
    public void testRestorePP() {
        CreatePuckemon createPuckemon = new CreatePuckemon();
        IPuckemon p = createPuckemon.createOwnedPuckemon(1,1);
        IPuckemon enemyP = createPuckemon.createOwnedPuckemon(1,1);

        //executes first attack in p
        int PPBefore = p.getMoveSet().get(0).getPP();
        p.getMoveSet().get(0).execute(p,enemyP);
        assertTrue(PPBefore-1 == p.getMoveSet().get(0).getPP());

        IEffect restorePP = new RestorePP(1);
        restorePP.execute(p,enemyP);

        assertTrue(PPBefore == p.getMoveSet().get(0).getPP());
    }
}
