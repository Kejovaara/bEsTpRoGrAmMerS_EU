package model.entities.puckemon;

import model.entities.CreatePuckemon;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestFixedPuckemon {

    @Test
    public void testMakeMove(){
        FixedPuckemon puckemon = new CreatePuckemon().createFixedPuckemon(2,2);

        assertEquals(true, puckemon.getMoveSet().size());  // The logical check
    }

}
