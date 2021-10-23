package model.effects.effectTypes;

import model.PTypes;
import model.effects.IEffect;
import services.puckemonGenerator.CreatePuckemon;
import model.entities.IPuckemon;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestRecoilDamage {

    @Test
    public void testRecoilDamage() {
        CreatePuckemon createPuckemon = new CreatePuckemon();
        IPuckemon p = createPuckemon.createOwnedPuckemon(1,1);
        IPuckemon enemyP = createPuckemon.createOwnedPuckemon(1,1);

        IEffect recoilDamage = new RecoilDamage(2, PTypes.NORMAL, 0.5f);
        int deltaPHP = p.getHealth();
        int deltaEnemyPHP = enemyP.getHealth();
        recoilDamage.execute(p, enemyP);
        deltaPHP -= p.getHealth();
        deltaEnemyPHP -= enemyP.getHealth();

        //The difference in hp taken should be 50% less for p
        assertTrue(deltaPHP == Math.round(deltaEnemyPHP*0.5f));
    }
}
