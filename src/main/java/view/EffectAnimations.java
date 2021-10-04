package view;

import model.entities.IPuckemon;

import java.util.ArrayList;
import java.util.List;

public class EffectAnimations {

    private static EffectAnimations instance;
    private List<EffectObserver> observers = new ArrayList<>();

    private EffectAnimations(){

    }

    public EffectAnimations getInstance(){
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


    public void displayDamage(int damage){
        for(EffectObserver observer: observers){
            observer.damageAnimation();
        }
    }

    public void displayHealing(int Heal){
        for(EffectObserver observer: observers){
            observer.HealAnimation();
        }
    }
}
