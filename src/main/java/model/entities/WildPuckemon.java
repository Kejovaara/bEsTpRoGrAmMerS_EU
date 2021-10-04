package model.entities;

import model.effects.IEffectContainer;

import java.util.Random;

public class WildPuckemon extends Puckemon implements IFighter{

    public WildPuckemon(int id, int level){
        super(id, level);
    }

//    public IEffectContainer getMoves(Player player) {
//        Random rand = new Random(); //instance of random class
//        int upperbound = 4;
//        //generate random values from 0-3
//        int int_random = rand.nextInt(upperbound);
//
//    }

}
