package view.message;

import org.lwjgl.Sys;
import view.MessageObserver;
import view.animation.TextAnimation;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for handling messages that are set during combat.
 */
public class MessageHandler {

    private static MessageHandler instance;
    private List<MessageObserver> observers = new ArrayList<>();
    private TextAnimation textAnimator;

    private MessageHandler(){

    }

    /**
     * @return the instance of the MessageHandler.
     */
    public static MessageHandler getInstance(){
        if(instance == null){
            instance = new MessageHandler();
        }
        return instance;
    }

    /**
     * Adds an observer to observe messages.
     * @param observer that observers messages.
     */
    public void addObserver(MessageObserver observer){
        observers.add(observer);
    }

    /**
     * Remove an observer from observing
     * @param observer to be removed
     */
    public void removeObserver(MessageObserver observer){
        observers.remove(observer);
    }

    public void setView(){
        //TODO: set a view variable so that this class can talk to it
    }

    /**
     * Displays a message
     * @param message to be displayed
     */
    public void DisplayMessage(String message){
        for(MessageObserver observer: observers){
            observer.SetMessage(message);
        }
    }

}
