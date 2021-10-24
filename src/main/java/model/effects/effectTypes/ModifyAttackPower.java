package model.effects.effectTypes;

import model.effects.IEffect;
import model.puckemon.IPuckemon;
import serviceControllers.observers.EffectHandler;


/**
 * A class that implements IEffect with the purpose of increasing either the
 * attackers or opponents attackPower
 * @author Rasmus Almryd
 */
public class ModifyAttackPower implements IEffect {


    private final int buffFactor;
    private final boolean buffOpponent;

    /**
     * Constructor of ModifyAttackPower, sets the attacker to be affected by the buffFactor.
     * @param buffFactor how much the attackPower should increase.
     */
    public ModifyAttackPower(int buffFactor) {
        this.buffFactor = buffFactor;
        this.buffOpponent = false;
    }

    /**
     * Constructor of ModifyAttackPower
     * @param buffFactor how much the attackPower should increase.
     * @param buffOpponent true if opponent should increase by the buffFactor, false if the attacker should.
     */
    public ModifyAttackPower(int buffFactor, boolean buffOpponent) {
        this.buffFactor = buffFactor;
        this.buffOpponent = buffOpponent;
    }

    /**
     * Depending on which IPuckemon that was chosen in the constructor, increase its attackPower by the buffFactor
     * @param attackUser the IPuckemon that used the effect
     * @param opponent the opposing IPuckemon
     */
    @Override
    public void execute(IPuckemon attackUser, IPuckemon opponent) {
        if(buffOpponent){
            opponent.modifyAttackPower(buffFactor);
            EffectHandler.getInstance().displayAttckBuff(buffFactor, opponent);
        }
        else {
            attackUser.modifyAttackPower(buffFactor);
            EffectHandler.getInstance().displayAttckBuff(buffFactor, attackUser);
        }

    }

}
