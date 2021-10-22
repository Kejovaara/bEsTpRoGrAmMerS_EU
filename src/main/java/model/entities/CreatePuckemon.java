package model.entities;

import model.ExcelReader;
import model.PTypes;
import model.entities.puckemon.OwnedPuckemon;
import model.entities.puckemon.FixedPuckemon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that returns/creates a type of Puckemon using the ExcelReader class.
 * @author Lukas Jigberg
 */
public class CreatePuckemon {

    ExcelReader excelReader = new ExcelReader();

    /**
     * buildOwned returns a OwnedPuckemon
     */
    private OwnedPuckemon buildOwned(int id, int level){
        List<Object> data = excelReader.getExcelData(id);
        return new OwnedPuckemon(id,level,(String)data.get(0), dissectListTypes((String)data.get(1)),(int) data.get(2),(int)data.get(3)
                ,(int)data.get(4),(int)data.get(5),(int)data.get(6),(int)data.get(7), dissectList((String)data.get(8)));
    }

    /**
     * buildFixed Returns a FixedPuckemon
     */
    private FixedPuckemon buildFixed(int id, int level){
        List<Object> data = excelReader.getExcelData(id);
        return new FixedPuckemon(id,level,(String)data.get(0), dissectListTypes((String)data.get(1)),(int) data.get(2),(int)data.get(3)
                ,(int)data.get(4),(int)data.get(5),dissectList((String)data.get(8)));
    }

    /**
     * dissect turns a string from the excel to a list of Types and Strings respectively
     */
    private List<PTypes> dissectListTypes(String cellString){
        return Arrays.stream(cellString.split(",\\s+"))
            .map(PTypes::valueOf)
            .collect(Collectors.toList());
    }

    private List<String> dissectList(String cellString){
        return new ArrayList<>(Arrays.asList(cellString.split(",")));
    }


    public OwnedPuckemon createOwnedPuckemon(int id, int level){
        return buildOwned(id,level);
    }

    public FixedPuckemon createFixedPuckemon(int id, int level){
        return buildFixed(id,level);
    }

}
