package services.observers;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for handling messages that are set from notifiers.
 * @author Emil Jonsson
 */
public class MessageHandler {

    private static MessageHandler instance;
    private final List<MessageObserver> observers = new ArrayList<>();

    private MessageHandler(){}


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
     * @param observer that observes messages.
     */
    public void addObserver(MessageObserver observer){
        observers.add(observer);
    }

    /**
     * Remove an observer from observing.
     * @param observer to be removed.
     */
    public void removeObserver(MessageObserver observer){
        observers.remove(observer);
    }

    /**
     * Displays a message to the observers.
     * @param message to be displayed.
     */
    public void DisplayMessage(String message){
        for(MessageObserver observer: observers){
            observer.SetMessage(message);
        }
    }

}
