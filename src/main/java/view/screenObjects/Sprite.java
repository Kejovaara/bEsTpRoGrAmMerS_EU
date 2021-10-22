package view.screenObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import run.Boot;
import view.IDraw;

/**
 * Screen object that renders Sprites on the screen
 */

public class Sprite implements IDraw {

    private final Boot game;
    private final SpriteBatch batch;
    private int xPos;
    private int yPos;
    private int width;
    private int height;
    private final Texture puckemon;

    /**
     * Constructor for Sprite
     * @param game the game
     * @param batch the batch
     * @param xPos the x-position where to print
     * @param yPos the y-position where to print
     * @param width the width of the sprite
     * @param height the height of the sprite
     * @param puckemon the puckemon
     */
    public Sprite(Boot game, SpriteBatch batch, int xPos, int yPos, int width, int height, Texture puckemon) {
        this.game = game;
        this.batch = batch;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.puckemon = puckemon;
    }

    private void render(){
        game.batch.draw(puckemon, xPos,yPos,width,height);
    }

    @Override
    public void draw() {
        render();
    }
}
