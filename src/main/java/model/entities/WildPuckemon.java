package model.entities;

import model.combat.Attack;
import model.effects.IEffectContainer;

import java.util.Random;

public class WildPuckemon extends Puckemon implements IFighter{

    public IEffectContainer getMoves(Player player) {
        Random rand = new Random(); //instance of random class
        int upperbound = 4;
        //generate random values from 0-3
        int int_random = rand.nextInt(upperbound);

        return puckemon.getAttack(int_random);
    }
}
