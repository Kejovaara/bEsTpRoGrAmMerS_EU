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
        return (String) interpretMonRegister(id).get(0);
    }
    public PTypes getType1(int id){
        return PTypes.valueOf((String) interpretMonRegister(id).get(1));
    }
    public PTypes getType2(int id){
        return PTypes.valueOf((String) interpretMonRegister(id).get(2));
    }
    public int getBaseHealth(int id){
        return (int) interpretMonRegister(id).get(3);
    }
    public int getBaseAttack(int id){
        return (int) interpretMonRegister(id).get(4);
    }
    public int  getBaseDefence(int id){
        return (int) interpretMonRegister(id).get(5);
    }
    public int  getBaseSpeed(int id){
        return (int) interpretMonRegister(id).get(6);
    }
    public int getEvolutionLevel(int id){
        return (int) interpretMonRegister(id).get(7);
    }
    public int getEvolutionId(int id){
        return (int) interpretMonRegister(id).get(8);
    }

}
