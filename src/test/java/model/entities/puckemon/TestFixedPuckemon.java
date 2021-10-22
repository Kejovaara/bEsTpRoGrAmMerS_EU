package model.entities.puckemon;

import model.attack.Attack;
import model.attack.AttackBuilder;
import model.entities.CreatePuckemon;
import model.entities.IPuckemon;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestFixedPuckemon {

    @Test
    public void testMakeMove(){
        FixedPuckemon puckemon = new CreatePuckemon().createFixedPuckemon(4,2);
        IPuckemon iPuckemon = new CreatePuckemon().createFixedPuckemon(4,2);

        Attack expectedAttack = AttackBuilder.createByName("Tackle");
        Attack attack = (Attack) puckemon.makeMove(iPuckemon);

        assertEquals(expectedAttack.getName(), attack.getName());

    }

}
