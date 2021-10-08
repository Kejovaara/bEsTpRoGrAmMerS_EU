package view.animation;

import model.entities.IPuckemon;
import model.entities.Puckemon;
import view.EffectObserver;

import java.util.ArrayList;
import java.util.List;

public class EffectAnimations {

    private static EffectAnimations instance;
    private List<EffectObserver> observers = new ArrayList<>();

    private EffectAnimations(){

    }

    public static EffectAnimations getInstance(){
        if(instance == null){
            instance = new EffectAnimations();
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
            observer.damageAnimation(damage, (Puckemon)damageReceiver);
        }
    }

    public void displayHealing(int heal, IPuckemon healReceiver){
        for(EffectObserver observer: observers){
            observer.healAnimation(heal , (Puckemon)healReceiver);
        }
    }

    public void displayBuff(int buff, String buffType, IPuckemon buffReceiver){
        for(EffectObserver observer: observers){
            observer.buffAnimation(buff , buffType, (Puckemon)buffReceiver);
        }
    }
}
