package view.screenObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import view.IRender;

/**
 * Screen object that renders images on the screen
 * @author Rasmus Almryd
 */

public class Image implements IRender {

    private final SpriteBatch batch;
    private int xPos;
    private int yPos;
    private final int width;
    private final int height;
    private final Texture texture;

    /**
     * Constructor for Image
     * @param batch the batch
     * @param xPos the position where to print
     * @param yPos the position where to print
     * @param width the width of the imaage
     * @param height the height of the image
     * @param texture the texture of the image
     */
    public Image(SpriteBatch batch, int xPos, int yPos, int width, int height, Texture texture) {
        this.batch = batch;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.texture = texture;
    }

    /**
     * Renders the image.
     */
    @Override
    public void render() {
        batch.draw(texture, xPos,yPos,width,height);
    }

    @Override
    public void setX(int x) {
        this.xPos = x;
    }

    @Override
    public void setY(int y) {
        this.yPos = y;
    }
    @Override
    public int getX() {
        return xPos;
    }

    @Override
    public int getY() {
        return yPos;
    }
}
