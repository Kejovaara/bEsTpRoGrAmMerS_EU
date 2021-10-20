package view.message;

import org.lwjgl.Sys;
import view.MessageObserver;
import view.animation.TextAnimation;

import java.util.ArrayList;
import java.util.List;

public class MessageHandler {

    private static MessageHandler instance;
    private List<MessageObserver> observers = new ArrayList<>();
    private TextAnimation textAnimator;

    private MessageHandler(){

    }

    public static MessageHandler getInstance(){
        if(instance == null){
            instance = new MessageHandler();
        }
        return instance;
    }

    public void addObserver(MessageObserver observer){
        observers.add(observer);
    }

    public void removeObserver(MessageObserver observer){
        observers.remove(observer);
    }

    public void setView(){
        //TODO: set a view variable so that this class can talk to it
    }

    public void DisplayMessage(String message){
        for(MessageObserver observer: observers){
            observer.SetMessage(message);
        }
    }

}
