package model;

import model.attack.Attack;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import java.util.ArrayList;

public class MonRegisterInterpreter {
    ArrayList baseStats;

    private ExcelReader excelReader = new ExcelReader();

    private ArrayList interpretMonRegister(int id){
        ArrayList baseStats = excelReader.getExcelData(id);
        return baseStats;
    }

    public String getName(int id){
        interpretMonRegister(id);
        return (String) baseStats.get(0);
    }
    public PTypes getType1(int id){
        interpretMonRegister(id);
        return PTypes.valueOf((String) baseStats.get(1));
    }
    public PTypes getType2(int id){
        interpretMonRegister(id);
        return PTypes.valueOf((String) baseStats.get(2));
    }
    public int getBaseHealth(int id){
        interpretMonRegister(id);
        return (int) baseStats.get(3);
    }
    public int getBaseAttack(int id){
        interpretMonRegister(id);
        return (int) baseStats.get(4);
    }
    public int  getBaseDefence(int id){
        interpretMonRegister(id);
        return (int) baseStats.get(5);
    }
    public int  getBaseSpeed(int id){
        interpretMonRegister(id);
        return (int) baseStats.get(6);
    }
    public int getEvolutionLevel(int id){
        interpretMonRegister(id);
        return (int) baseStats.get(7);
    }
    public int getEvolutionId(int id){
        interpretMonRegister(id);
        return (int) baseStats.get(8);
    }

}
