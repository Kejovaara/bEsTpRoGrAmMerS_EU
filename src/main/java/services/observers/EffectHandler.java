package services.observers;

import model.entities.puckemon.IPuckemon;
import model.entities.puckemon.Puckemon;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for handling effect animations.
 * @author Rasmus Almryd
 */
public class EffectHandler {

    private static EffectHandler instance;
    private final List<EffectObserver> observers = new ArrayList<>();

    private EffectHandler(){}

    /**
     * @return the instance for the effect animation.
     */
    public static EffectHandler getInstance(){
        if(instance == null){
            instance = new EffectHandler();
        }
        return instance;
    }

    /**
     * Add an observer for observing effects.
     * @param observer is the observer for observing effects.
     */
    public void addObserver(EffectObserver observer){
        observers.add(observer);
    }

    /**
     * Removes an observer from observing effects.
     * @param observer the given observer.
     */
    public void removeObserver(EffectObserver observer){
        observers.remove(observer);
    }

    /**
     * Displays the damage that occurs.
     * @param damage the amount of damage.
     * @param damageReceiver the recipient of the damage (where to print it).
     */
    public void displayDamage(int damage, IPuckemon damageReceiver){
        for(EffectObserver observer: observers){
            observer.onDamage(damage, (Puckemon)damageReceiver);
        }
    }

    /**
     * Displays the healing that occurs.
     * @param heal the amount of healing.
     * @param healReceiver the recipient of the heals (where to print it).
     */
    public void displayHealing(int heal, IPuckemon healReceiver){
        for(EffectObserver observer: observers){
            observer.onHeal(heal , (Puckemon)healReceiver);
        }
    }

    /**
     * Displays the Attack buff that occurs.
     * @param buff how much buff.
     * @param buffReceiver the recipient of the buff (where to print it).
     */
    public void displayAttckBuff(int buff, IPuckemon buffReceiver){
        for(EffectObserver observer: observers){
            observer.onAttackBuff(buff, (Puckemon)buffReceiver);
        }
    }
}
