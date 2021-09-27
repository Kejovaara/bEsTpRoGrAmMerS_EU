package model.entities;

import model.PuckemonTypes;
import model.combat.Attack;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class Puckemon {

    protected String name;
    protected PuckemonTypes type1;
    protected PuckemonTypes type2;

    protected int baseHealth;
    protected int baseAttackPower;
    protected int baseDefence;
    protected int baseSpeed;

    protected int level;

    protected int evolutionLevel = 101;
    protected int evolutionID = 0;

    // -------------

    /**
     * These are a pokemons stats. They are solely based on the baseStats and current level.
     */
    protected int maxHealth;
    protected int attackPower;
    protected int defence;
    protected int speed;

    /**
     * These are a pokemons stats during the current combat. They can be altered.
     */
    protected int currentHealth;
    protected int currentAttackPower;
    protected int currentDefence;
    protected int currentSpeed;

    protected int attackPowerBuffFactor = 0;
    protected int defenceBuffFactor = 0;
    protected int speedBuffFactor = 0;

    protected ArrayList<Attack> moveList = new ArrayList<Attack>();
    protected ArrayList<Attack> moveSet = new ArrayList<Attack>(4);

    private void readExcelFile(int id){
        try
        {
            FileInputStream file = new FileInputStream(new File("src/main/java/model/MonRegister.xlsx"));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFRow row = sheet.getRow(id);
            name = row.getCell(1).getStringCellValue();
//            type1 = row.getCell(2).getStringCellValue();
//            type2 = row.getCell(3).getStringCellValue();
            baseHealth = (int) row.getCell(4).getNumericCellValue();
            baseAttackPower = (int) row.getCell(5).getNumericCellValue();
            baseDefence = (int) row.getCell(6).getNumericCellValue();
            baseSpeed = (int) row.getCell(7).getNumericCellValue();
            evolutionLevel = (int) row.getCell(8).getNumericCellValue();
            evolutionID = (int) row.getCell(9).getNumericCellValue();

            file.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    protected void buildPuckemon(int id){


        readExcelFile(id);
        // Collects data from Excel depending on the ID
        // ex: this.baseSpeed = "excel.baseSpeed id 1"
        // Fill the moveList
        calculateLevelStats();
    }

    protected void calculateLevelStats(){
        this.maxHealth = (2*baseHealth+level)/100 + level + 10;
        this.attackPower = (2*baseAttackPower+level)/100+5;
        this.defence = (2*baseDefence+level)/100+5;
        this.speed = (2*baseSpeed+level)/100+5;
    }

    protected void alterCurrentStats(){
        if (attackPowerBuffFactor < 0){
            currentAttackPower = (int) (attackPower) * (2 / (2 + (-1) * attackPowerBuffFactor));
        }else{
            currentAttackPower = (int) (attackPower * (1 + attackPowerBuffFactor * 0.25));
        }
        if (defenceBuffFactor < 0){
            currentDefence = (int) (defence) * (2 / (2 + (-1) * defenceBuffFactor));
        }else{
            currentDefence = (int) (defence * (1 + defenceBuffFactor * 0.25));
        }
        if (speedBuffFactor < 0){
            currentSpeed = (int) (speed) * (2 / (2 + (-1) * speedBuffFactor));
        }else{
            currentSpeed = (int) (speed * (1 + speedBuffFactor * 0.25));
        }
    }

}
