package model.entities;

import model.combat.Attack;

import java.util.ArrayList;

public class Puckemon {
    private String name;
    private int health;

    private ArrayList<Attack> attackList = new ArrayList<Attack>(4);

    public Puckemon(){

    }

//    private Attack[] attacks = {
//            Attack attack1 = new Attack();
//            Attack attack2 = new Attack();
//            Attack attack3 = new Attack();
//            Attack attack4 = new Attack();
//    }

    public Attack getAttack(int attackNr) { return attackList.get(attackNr); }

    public void makeMove(int attackNr) {  }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }
}
