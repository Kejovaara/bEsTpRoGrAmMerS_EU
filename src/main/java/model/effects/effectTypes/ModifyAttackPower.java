package model.effects.effectTypes;

import model.effects.IEffect;
import model.entities.IPuckemon;

public class ModifyAttackPower implements IEffect {


    private int buffFactor;
    private boolean buffOpponent;

    public ModifyAttackPower(int buffFactor) {
        this.buffFactor = buffFactor;
        this.buffOpponent = false;
    }

    public ModifyAttackPower(int buffFactor, boolean buffOpponent) {
        this.buffFactor = buffFactor;
        this.buffOpponent = buffOpponent;
    }

    @Override
    public void execute(IPuckemon attackUser, IPuckemon opponent) {
        if(buffOpponent){
            opponent.modifyAttackPower(buffFactor);
        }
        else {
            attackUser.modifyAttackPower(buffFactor);
        }

    }

}
