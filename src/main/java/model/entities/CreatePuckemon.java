package model.entities;

import model.ExcelReader;
import model.PTypes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that returns/creates a type of Puckemon using the ExcelReader class.
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
     * buildVild Returns a VildPuckemon
     */
    private VildPuckemon buildVild(int id, int level){
        List<Object> data = excelReader.getExcelData(id);
        return new VildPuckemon(id,level,(String)data.get(0), dissectListTypes((String)data.get(1)),(int) data.get(2),(int)data.get(3)
                ,(int)data.get(4),(int)data.get(5),dissectList((String)data.get(8)));
    }

    /**
     * dissect turns the string from excel to list of Types and String respectively
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

    public VildPuckemon createVildPuckemon(int id, int level){
        return buildVild(id,level);
    }

}
