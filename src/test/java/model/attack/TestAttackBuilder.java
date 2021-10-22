package model.attack;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
public class TestAttackBuilder {

    @Test
    public void testAttackBuilderException(){
        String testAttackName = "test";
        try {
            Attack attack = AttackBuilder.createByName(testAttackName);
            assertTrue(false);
        }catch (IllegalArgumentException e){
            assertTrue(e.getMessage().equals("Unknown Attack " + testAttackName));
        }
    }
}
