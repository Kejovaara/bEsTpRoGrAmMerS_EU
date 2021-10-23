package services.puckemonGenerator;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestExcelReader {

    @Test
    public void testGetExcelData() {
        ExcelReader excelReader = new ExcelReader();
        List baseStats = excelReader.getExcelData(2);

        ArrayList expected = new ArrayList();
        expected.add("Ivysaur");
        expected.add("GRASS, POISON");
        expected.add(60);
        expected.add(62);
        expected.add(63);
        expected.add(60);
        expected.add(36);
        expected.add(3);
        expected.add("Tackle,SwordsDance");

        assertEquals(baseStats, expected);  // The logical check
    }

}
