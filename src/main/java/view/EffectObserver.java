package view;

import model.entities.Puckemon;

public interface EffectObserver {

    public void damageAnimation(int damage, Puckemon damageReceiver);
    public void healAnimation(int heal, Puckemon healReceiver);
    public void buffAnimation(int buff, String buffType, Puckemon buffReceiver);

}
