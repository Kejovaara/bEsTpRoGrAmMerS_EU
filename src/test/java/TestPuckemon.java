import model.entities.CreatePuckemon;
import org.junit.Test;

import static java.lang.System.out;
import static org.junit.Assert .*;

import model.attack.Attack;
import model.attack.AttackFactory;
import model.effects.IEffectContainer;
import model.entities.OwnedPuckemon;
import model.entities.Puckemon;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

    public class TestPuckemon {

        private CreatePuckemon createPuckemon = new CreatePuckemon();

        @Test
        public void testCalculateLevelStats(){
            OwnedPuckemon puckemon =  createPuckemon.createOwnedPuckemon(1,5);
            int expected = (2*45*5)/100 + 5 + 10;

            assertEquals(expected, puckemon.getHealth());  // The logical check
        }

        @Test
        public void testAlterCurrentStatPos(){
            OwnedPuckemon puckemon =  createPuckemon.createOwnedPuckemon(1,5);
            puckemon.modifyAttackPower(4);
            int expected = 18;

            assertEquals(expected, puckemon.getAttackPower());  // The logical check
        }

        @Test
        public void testAlterCurrentStatNeg(){
            OwnedPuckemon puckemon =  createPuckemon.createOwnedPuckemon(1,5);
            puckemon.modifyAttackPower(-4);
            int expected = 3;

            assertEquals(expected, puckemon.getAttackPower());  // The logical check
        }

        @Test
        public void testFillMoveSetMax4Moves(){
            OwnedPuckemon puckemon =  createPuckemon.createOwnedPuckemon(128,5);
            int expected = 4;

            assertEquals(expected, puckemon.getMoveSet().size());  // The logical check
        }

        @Test
        public void testFillMoveSetOnly3Moves(){
            OwnedPuckemon puckemon =  createPuckemon.createOwnedPuckemon(3,5);
            int expected = 3;
            assertEquals(expected, puckemon.getMoveSet().size());  // The logical check
        }
    }
