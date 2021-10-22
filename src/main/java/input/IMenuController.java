package input;


/**
 * A common Interface for all controllers that interact with a Menu.
 */
public interface IMenuController {
    /**
     * Method is called when the selected MenuItem in a Menu changes.
     * @param index the index of the newly selected MenuItem in a Menu.
     */
    void onCursorMove(int index);

    /**
     * Method is called when the Menu wants to activate a certain MenuItem.
     * @param index the index of the selected MenuItem in a Menu.
     */
    void onCursorEnter(int index);
}
