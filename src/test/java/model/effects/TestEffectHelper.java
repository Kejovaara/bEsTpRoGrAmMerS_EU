package model.effects;

import static org.junit.Assert.*;

import model.PTypes;
import services.puckemonGenerator.CreatePuckemon;
import model.entities.IPuckemon;
import org.junit.Test;

public class TestEffectHelper {

    @Test
    public void testCalculateDamage() {
        CreatePuckemon createPuckemon = new CreatePuckemon();
        IPuckemon p = createPuckemon.createOwnedPuckemon(1,1);
        IPuckemon enemyP = createPuckemon.createOwnedPuckemon(1,1);

        int power = 2;
        PTypes attackType = PTypes.NORMAL;

        int damage = EffectHelper.calculateDamage(p,enemyP,power,attackType);

        double dividendPart = ((float)(2/5) + 2) * power * ((float)5/(float)5);
        double dividePart = (float)(dividendPart / 50) + 2;
        double highDamage = (int)Math.round(dividePart * 1.2 * 1);
        double lowDamage = (int)Math.round(dividePart * 1 * 1);

        assertTrue( lowDamage <= damage && damage <= highDamage);  // The logical check
    }

    @Test
    public void testSTAB(){
        CreatePuckemon createPuckemon = new CreatePuckemon();
        IPuckemon p = createPuckemon.createOwnedPuckemon(1,1);
        IPuckemon enemyP = createPuckemon.createOwnedPuckemon(1,1);

        int power = 2;
        PTypes attackType = PTypes.POISON;

        int damage = EffectHelper.calculateDamage(p,enemyP,power,attackType);

        double dividendPart = ((float)(2/5) + 2) * power * ((float)5/(float)5);
        double dividePart = (float)(dividendPart / 50) + 2;
        double highDamage = (int)Math.round(dividePart * 1.2 * 1.5);
        double lowDamage = (int)Math.round(dividePart * 1 * 1.5);

        assertTrue( lowDamage <= damage && damage <= highDamage);  // The logical check
    }

}
