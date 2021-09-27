package model.effects.effectTypes;

import model.PTypes;
import model.effects.IEffect;
import model.entities.IPuckemon;

public class HpSteal implements IEffect {

    private int damage;

    public HpSteal(int damage){
        this.damage = damage;
    }

    //INTE KLAR
    public void execute(IPuckemon attackUser, IPuckemon opponent){
        int finalDamage = damage;
        if(opponent.getType() == PTypes.FIRE && attackUser.getType() == PTypes.ROCK){
            finalDamage -= 20;
        }
        opponent.setHealth(opponent.getHealth() - finalDamage);
        attackUser.setHealth(attackUser.getHealth() + (finalDamage/2));
    }

}
