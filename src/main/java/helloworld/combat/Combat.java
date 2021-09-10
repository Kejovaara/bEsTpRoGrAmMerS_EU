package helloworld.combat;

public class Combat {

    ICombat player;
    ICombat opponent;

    public Combat(ICombat player, ICombat opponent){
        this.player = player;
        this.opponent = opponent;

    }
}
