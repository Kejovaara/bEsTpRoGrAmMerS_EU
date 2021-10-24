package serviceControllers.observers;

import model.puckemon.Puckemon;

/**
 * An observer interface to listen on executed effects in model.
 * @author Rasmus Almryd
 */
public interface EffectObserver {

    /**
     * Called when damage is dealt by an effect.
     * @param damage damage dealt by effect.
     * @param damageReceiver the recipient of the damage.
     */
    void onDamage(int damage, Puckemon damageReceiver);

    /**
     * Called when heal is done by effect
     * @param heal restores int amount of Hp to healReceiver.
     * @param healReceiver The puckemon receiving the healing.
     */
    void onHeal(int heal, Puckemon healReceiver);

    /**
     * Called when buff is done by an effect.
     * @param buff Buffs (increases stat) of the buff receiver.
     * @param buffReceiver The one receiver.
     */
    void onAttackBuff(int buff, Puckemon buffReceiver);

}
