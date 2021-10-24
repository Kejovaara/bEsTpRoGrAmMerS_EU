package controller;


/**
 * A common Interface for all controllers that interact with a Menu.
 * @author Rasmus Almryd
 */
public interface IMenuController {

    /**
     * Method is called when the Menu wants to activate a certain MenuItem.
     * @param index the index of the selected MenuItem in a Menu.
     */
    void onCursorEnter(int index);
}
