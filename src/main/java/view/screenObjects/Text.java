package view.screenObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import view.IRender;

/**
 * Screen object that renders static text on the screen.
 * @author Lukas Jigberg
 */
public class Text implements IRender {

    private final BitmapFont font;
    private final SpriteBatch batch;
    private int xPos;
    private int yPos;
    private String message;
    private final Color color;

    /**
     * Constructor for Text.
     * @param font the font.
     * @param batch the batch.
     * @param color the color.
     * @param xPos the x-position where to print.
     * @param yPos the y-position where to print.
     * @param message the message to display.
     * @param scale the scale of the text.
     */
    public Text(BitmapFont font, SpriteBatch batch, Color color, int xPos, int yPos, String message, float scale) {
        this.font = font;
        this.batch = batch;
        this.xPos = xPos;
        this.yPos = yPos;
        this.message = message;
        this.color = color;
        font.getData().setScale(scale);
        //font.setColor(color.get(0), color.get(1), color.get(2),1);
    }

    /**
     * Sets the message of the text.
     * @param message message to set text to.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Renders text.
     */
    @Override
    public void render(){
        font.setColor(color);
        font.draw(batch, message, xPos, yPos);
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
