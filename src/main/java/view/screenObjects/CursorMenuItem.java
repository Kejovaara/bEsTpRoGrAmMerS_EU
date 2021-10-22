package view.screenObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import view.IRender;

/**
 * Screen object that renders CursorMenu items on the screen
 */
public class CursorMenuItem implements IRender {

    private SpriteBatch batch;
    private  Text text;
    private Image arrow;
    private Boolean active;
    private int xPos,yPos;
    private float scale;
    private String message;

    private BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"), Gdx.files.internal("fonts/pixelfont.png"), false);

    /**
     * Constructor for CurstorMenuItem
     * @param batch the batch
     * @param message the attack
     * @param xPos the position where to print
     * @param yPos the position where to print
     * @param scale the scale of the object to print
     * @param active whether the item is active or not
     */
    public CursorMenuItem(SpriteBatch batch, String message, int xPos, int yPos, float scale, Boolean active){
        this.batch = batch;
        this.active = active;
        this.xPos = xPos;
        this.yPos = yPos;
        this.scale = scale;
        this.message = message;

        this.text = new Text(font, batch, Color.BLACK, xPos, yPos,message,scale);
        Texture arrow = new Texture(Gdx.files.internal("Arrow.png"));
        this.arrow = new Image(batch,xPos-25,yPos-15,16,16,arrow);
    }

    /**
     * Change the color of the Cursor item
     * @param color the given color
     */
    protected void changeTextColor(Color color){
        this.text = new Text(font, batch, color, xPos, yPos, message ,scale);
    }

    @Override
    public void render() {
        text.render();
        if (active) arrow.render();
    }

    @Override
    public void setX(int x) {
        this.xPos = x;
        text.setX(x);
        arrow.setX(x-25);
    }

    @Override
    public void setY(int y) {
        this.yPos = y;
        text.setY(y);
        arrow.setY(y-15);
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
