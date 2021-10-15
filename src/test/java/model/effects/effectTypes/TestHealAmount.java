//package model.effects.effectTypes;
//
//import static org.junit.Assert.*;
//
//import model.PTypes;
//import model.entities.IPuckemon;
//import model.entities.OwnedPuckemon;
//import org.junit.Test;
//
//public class TestHealAmount {
//
//    @Test
//    public void testHealYourself() {
//        IPuckemon p = new OwnedPuckemon(1,1);
//        IPuckemon enemyP = new OwnedPuckemon(1,1);
//
//
//        DoDamage damage = new DoDamage(50, PTypes.NORMAL);
//        damage.execute(enemyP, p);
//        int health = p.getHealth();
//        int amount = 1;
//        int expected = health + amount;
//
//        HealAmount heal = new HealAmount(amount, true);
//        heal.execute(p, enemyP);
//
//        assertEquals(p.getHealth(), expected);
//    }
//
//    @Test
//    public void testHealOpponent() {
//        IPuckemon p = new OwnedPuckemon(1,1);
//        IPuckemon enemyP = new OwnedPuckemon(1,1);
//
//
//        DoDamage damage = new DoDamage(50, PTypes.NORMAL);
//        damage.execute(p, enemyP);
//        int health = enemyP.getHealth();
//        int amount = 1;
//        int expected = health + amount;
//
//        HealAmount heal = new HealAmount(amount, false);
//        heal.execute(p, enemyP);
//
//        assertEquals(enemyP.getHealth(), expected);
//    }
//
//}
