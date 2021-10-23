package model.attack;

import model.effects.IEffect;
import services.puckemonGenerator.CreatePuckemon;
import model.entities.IPuckemon;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class TestAttacks {

    @Test
    public void testAttackPriority() {
        Attack a1 = AttackBuilder.getQuickAttack();
        assertTrue(a1.getPriority() == 1);
        Attack a2 = AttackBuilder.getSwordsDance();
        assertTrue(a2.getPriority() == 3);

    }

    @Test
    public void testAttackPP() {
        CreatePuckemon createPuckemon = new CreatePuckemon();
        IPuckemon p = createPuckemon.createOwnedPuckemon(1,1);
        IPuckemon enemyP = createPuckemon.createOwnedPuckemon(1,1);

        Attack a1 = AttackBuilder.getQuickAttack();
        List<IEffect> effects = a1.getEffects();
        assertTrue(effects.size() == 1);
        assertTrue(a1.getBasePP()-1 == a1.getPP());

    }
}
