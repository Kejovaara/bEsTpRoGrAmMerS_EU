package view.animation;

import model.entities.IPuckemon;
import model.entities.Puckemon;
import view.EffectObserver;

import java.util.ArrayList;
import java.util.List;

public class EffectAnimationsHandler {

    private static EffectAnimationsHandler instance;
    private List<EffectObserver> observers = new ArrayList<>();

    private EffectAnimationsHandler(){

    }

    public static EffectAnimationsHandler getInstance(){
        if(instance == null){
            instance = new EffectAnimationsHandler();
        }
        return instance;
    }

    public void addObserver(EffectObserver observer){
        observers.add(observer);
    }

    public void removeObserver(EffectObserver observer){
        observers.remove(observer);
    }

    public void setView(){
        //TODO: set a view variable so that this class can talk to it
    }


    public void displayDamage(int damage, IPuckemon damageReceiver){
        for(EffectObserver observer: observers){
            observer.onDamage(damage, (Puckemon)damageReceiver);
        }
    }

    public void displayHealing(int heal, IPuckemon healReceiver){
        for(EffectObserver observer: observers){
            observer.onHeal(heal , (Puckemon)healReceiver);
        }
    }

    public void displayAttckBuff(int buff, IPuckemon buffReceiver){
        for(EffectObserver observer: observers){
            observer.onAttackBuff(buff, (Puckemon)buffReceiver);
        }
    }

    public enum EffectType{
        HEAL,
        DAMAGE,
        ATTACKBUFF,
        DEFENCEBUFF,
        SPEEDBUFF,
    }
}
