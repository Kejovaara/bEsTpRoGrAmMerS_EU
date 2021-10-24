package serviceControllers.observers;

/**
 * Observer used for sending message to observers
 * @author EmilJonsson
 */
public interface MessageObserver {

    /**
     * Sets a message to observes.
     * @param message a message to the observers.
     */
    void SetMessage(String message);
}
