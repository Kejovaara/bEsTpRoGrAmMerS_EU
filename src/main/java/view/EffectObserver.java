package view;

import model.entities.puckemon.Puckemon;

public interface EffectObserver {

    void onDamage(int damage, Puckemon damageReceiver);
    void onHeal(int heal, Puckemon healReceiver);
    void onAttackBuff(int buff, Puckemon buffReceiver);

    //public void onUsedEffect(int effectPower, EffectAnimationsHandler.EffectType effectType, Puckemon Receiver);
}
