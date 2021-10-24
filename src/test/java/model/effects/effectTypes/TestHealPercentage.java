package model.effects.effectTypes;

import static org.junit.Assert.*;

import model.PTypes;
import serviceControllers.puckemonGenerator.CreatePuckemon;
import model.puckemon.IPuckemon;
import org.junit.Test;

public class TestHealPercentage {

    @Test
    public void testHealYourself() {
        CreatePuckemon createPuckemon = new CreatePuckemon();
        IPuckemon p = createPuckemon.createOwnedPuckemon(1,1);
        IPuckemon enemyP = createPuckemon.createOwnedPuckemon(1,1);

        DoDamage damage = new DoDamage(50, PTypes.NORMAL);
        damage.execute(enemyP, p);
        int health = p.getHealth();
        float amount = 100f;
        int expected = Math.min((health + Math.round(health * amount/100)), p.getMaxHealth());

        HealPercentage heal = new HealPercentage(amount, true);
        heal.execute(p, enemyP);


        assertEquals(p.getHealth(), expected);  // The logical check
    }

    @Test
    public void testHealOpponent() {
        CreatePuckemon createPuckemon = new CreatePuckemon();
        IPuckemon p = createPuckemon.createOwnedPuckemon(1,1);
        IPuckemon enemyP = createPuckemon.createOwnedPuckemon(1,1);


        DoDamage damage = new DoDamage(50, PTypes.NORMAL);
        damage.execute(p, enemyP);
        int health = enemyP.getHealth();
        float amount = 100f;
        int expected = Math.min((health + Math.round(health * amount/100)), p.getMaxHealth());

        HealPercentage heal = new HealPercentage(amount, false);
        heal.execute(p, enemyP);

        assertEquals(enemyP.getHealth(), expected);  // The logical check
    }

    @Test
    public void testHealOnlyPercentage(){
        CreatePuckemon createPuckemon = new CreatePuckemon();
        IPuckemon p = createPuckemon.createOwnedPuckemon(1,1);
        IPuckemon enemyP = createPuckemon.createOwnedPuckemon(1,1);

        DoDamage damage = new DoDamage(50, PTypes.NORMAL);
        damage.execute(enemyP, p);
        int health = p.getHealth();
        float amount = 100f;
        int expected = Math.min((health + Math.round(health * amount/100)), p.getMaxHealth());

        HealPercentage heal = new HealPercentage(amount);
        heal.execute(p, enemyP);


        assertEquals(p.getHealth(), expected);  // The logical check
    }

}
