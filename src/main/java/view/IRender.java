package view;

/**
 * Iterface used for rendering
 * @author Rasmus Almryd
 */
public interface IRender {
    /**
     * Renders to the screen
     */
    public void render();

    /**
     * Sets the x position
     * @param x
     */
    public void setX(int x);
    /**
     * Sets the y position
     * @param y
     */
    public void setY(int y);

    /**
     * Gets x positon;
     * @return int X position
     */
    public int getX();
    /**
     * Gets y positon;
     * @return int y position
     */
    public int getY();
}
