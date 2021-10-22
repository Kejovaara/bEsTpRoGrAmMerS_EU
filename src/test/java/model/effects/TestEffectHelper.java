//package model.effects;
//
//import static org.junit.Assert.*;
//
//import model.PTypes;
//import model.entities.IPuckemon;
//import model.entities.puckemon.OwnedPuckemon;
//import org.junit.Test;
//
//public class TestEffectHelper {
//
//    @Test
//    public void testCalculateDamage() {
//
//
//        IPuckemon attackUser = new OwnedPuckemon(1,1);
//        IPuckemon opponent = new OwnedPuckemon(1,1);
//        int power = 2;
//        PTypes attackType = PTypes.NORMAL;
//
//        int damage = EffectHelper.calculateDamage(attackUser,opponent,power,attackType);
//
//        double dividendPart = ((float)(2/5) + 2) * power * ((float)5/(float)5);
//        double dividePart = (float)(dividendPart / 50) + 2;
//        double highDamage = (int)Math.round(dividePart * 1.5 * 1);
//        double lowDamage = (int)Math.round(dividePart * 1 * 1);
//
//        assertTrue( lowDamage <= damage && damage <= highDamage);  // The logical check
//    }
//
//}
