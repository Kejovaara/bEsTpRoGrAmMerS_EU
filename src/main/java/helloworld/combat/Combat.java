package helloworld.combat;

import java.util.List;

public class Combat {

    ICombat player;
    ICombat opponent;

    public Combat(ICombat player, ICombat opponent){
        this.player = player;
        this.opponent = opponent;

    }

    public void playerAttack(){
        Attack attack = player.getAttack();
        List<Effect> effects = attack.getEffects();
        for (Effect effect: effects) {
            effect.execute(player, opponent);
        }
        opponent.makeAttack(attack);
    }

}
