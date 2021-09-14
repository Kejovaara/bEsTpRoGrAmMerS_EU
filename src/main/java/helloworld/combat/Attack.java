package helloworld.combat;

import helloworld.PuckemonTypes;

import java.util.ArrayList;
import java.util.List;

public class Attack {
    private int damage;
    private PuckemonTypes type;
    private List effects = new ArrayList<Effect>();

    public void execute(){

    }

    public List<Effect> getEffects(){
        return effects;
    }
}
