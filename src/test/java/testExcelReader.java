import static java.lang.System.out;
import static org.junit.Assert.*;

import model.ExcelReader;
import model.MonRegisterInterpreter;
import model.attack.Attack;
import model.attack.AttackFactory;
import model.combat.Combat;
import model.effects.IEffectContainer;
import model.entities.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class testExcelReader {

    @Test
    public void testGetExcelData() {
        ExcelReader excelReader = new ExcelReader();
        ArrayList baseStats = excelReader.getExcelData(2);

        ArrayList expected = new ArrayList();
        expected.add("Ivysaur");
        expected.add("GRASS");
        expected.add("POISON");
        expected.add(60);
        expected.add(62);
        expected.add(63);
        expected.add(60);
        expected.add(36);
        expected.add(3);
        expected.add("Tackle,SwordsDance");
        assertTrue(baseStats.equals(expected));  // The logical check
    }

}
