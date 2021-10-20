package model.entities;

import model.PTypes;
import model.attack.AttackFactory;
import model.effects.IEffectContainer;

import java.util.List;
import java.util.Random;

public class VildPuckemon extends Puckemon implements IFighter{

    public VildPuckemon(int id, int level, String name, List<PTypes> types, int baseHealth, int baseAttackPower, int baseDefence, int baseSpeed, List<String> moveList) {
        super(id, level, name, types, baseHealth, baseAttackPower, baseDefence, baseSpeed, moveList);
    }

    public IEffectContainer makeMove(IPuckemon enemyP) {
        Random rand = new Random(); //instance of random class
        int upperbound = 4;
        //generate random values from 0-3
        int int_random = rand.nextInt(upperbound);

        //TODO: make random attack
        return AttackFactory.getTackle();
    }

    @Override
    public boolean checkIfDefeated(){
        boolean defeated = currentHealth <= 0;
        return defeated;
    }

    @Override
    public IPuckemon getActivePuckemon() {
        return null;
    }

}
