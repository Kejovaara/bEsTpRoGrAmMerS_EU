package view.screenObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import run.Boot;
import view.IRender;

public class Image implements IRender {

    private final SpriteBatch batch;
    private int xPos;
    private int yPos;
    private int width;
    private int height;
    private Texture texture;

    public Image(SpriteBatch batch, int xPos, int yPos, int width, int height, Texture texture) {
        this.batch = batch;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.texture = texture;
    }

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
