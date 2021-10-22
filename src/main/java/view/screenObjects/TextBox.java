package view.screenObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import view.IRender;

import java.util.List;

/**
 * Labels work different from most libGdx, it isn't rendered, it is added to a Stage.
 */

public class TextBox implements IRender {

    private final Label label;
    private int xPos, yPos;
    private Color color;
    private SpriteBatch batch;

    /**
     * Constructor for TextBox
     * @param font the font
     * @param batch the batch
     * @param color the color
     * @param xPos the x-position where to print
     * @param yPos the y-position where to print
     * @param width the width of the textbox
     * @param height the height of the textbox
     * @param wrap whether to wrap the text or not
     * @param message the message to display
     * @param scale the scale of the Textbox
     */
    public TextBox(BitmapFont font, SpriteBatch batch, Color color, int xPos, int yPos, int width, int height, boolean wrap, String message, float scale) {
        this.color = color;
        this.xPos = xPos;
        this.yPos = yPos;
        this.batch = batch;

        LabelStyle fontStyle = new LabelStyle();
        font.getData().setScale(scale);
        fontStyle.font = font;
        fontStyle.fontColor = color;

        label = new Label(message,fontStyle);
        label.setSize(width,height);
        label.setPosition(xPos,yPos);
        label.setWrap(wrap);
    }

    /**
     * @return the label
     */
    public Label getLabel(){
        return label;
    }

    /**
     * Set the text of a label
     * @param text the text to be set
     */
    public void setText(String text){
        label.setText(text);
    }

    @Override
    public void render() {
        label.draw(batch, 1);
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
