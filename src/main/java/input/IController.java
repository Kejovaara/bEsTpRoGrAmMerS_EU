package input;


/**
 * Common Interface for standard Controllers (not IMenuControllers).
 * @author Rasmus Almryd
 */
public interface IController {

    /**
     * Checks for inputs and model states and according to that executes actions on the game and model.
     */
    void update();
}
