package model.entities;
import model.combat.Attack;

import java.util.Random;


public class WildPuckemon implements IOpponent {
    private Puckemon puckemon = new Puckemon();

    public void makeMove(Player player) {
        Random rand = new Random(); //instance of random class
        int upperbound = 4;
        //generate random values from 0-3
        int int_random = rand.nextInt(upperbound);

//        return getAttack(int_random);
    }

    public Puckemon getPuckemon() {
        return puckemon;
    }

    private Attack getAttack(int attackNr) {
        return puckemon.getAttack(attackNr);
    }
}
