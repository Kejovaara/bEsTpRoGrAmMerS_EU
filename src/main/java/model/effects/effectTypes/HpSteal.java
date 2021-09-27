package model.effects.effectTypes;

import model.PuckemonTypes;
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
        if(opponent.getType() == PuckemonTypes.FIRE && attackUser.getType() == PuckemonTypes.ROCK){
            finalDamage -= 20;
        }
        opponent.setHealth(opponent.getHealth() - finalDamage);
        attackUser.setHealth(attackUser.getHealth() + (finalDamage/2));
    }

}
