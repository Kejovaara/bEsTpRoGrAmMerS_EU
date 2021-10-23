package view;

/**
 * Iterface used for rendering.
 * @author Rasmus Almryd
 */
public interface IRender {
    /**
     * Renders to the screen.
     */
    void render();

    /**
     * Sets the x position.
     * @param x x postion
     */
    void setX(int x);
    /**
     * Sets the y position.
     * @param y y position
     */
    void setY(int y);

    /**
     * Gets x positon;
     * @return int X position.
     */
    int getX();
    /**
     * Gets y positon;
     * @return int y position.
     */
    int getY();
}
