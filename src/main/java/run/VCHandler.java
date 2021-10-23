package run;

import input.InputController;
import view.Screens;

/**
 * Interface for a View-Controller handler. Used to switch the active view and controller.
 * @author Rasmus Almryd
 */
public interface VCHandler {

    /**
     * Sets the active view.
     * @param screen the view to switch to.
     */
    void setView(Screens screen);

    /**
     * Sets the active Controller.
     * @param controller the controller to switch to.
     */
    void setController(InputController.Controllers controller);
}
