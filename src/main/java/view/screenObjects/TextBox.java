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

    public Label getLabel(){
        return label;
    }

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
