package view;

import model.entities.Puckemon;

public interface EffectObserver {

    public void onDamage(int damage, Puckemon damageReceiver);
    public void onHeal(int heal, Puckemon healReceiver);
    public void onAttackBuff(int buff, Puckemon buffReceiver);

    //public void onUsedEffect(int effectPower, EffectAnimationsHandler.EffectType effectType, Puckemon Receiver);
}
