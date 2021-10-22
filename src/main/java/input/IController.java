package input;


/**
 * Common Interface for standard Controllers (not IMenuControllers)
 */
public interface IController {

    /**
     * Checks for inputs and according to that executes actions on the game and model.
     */
    void update();
}
