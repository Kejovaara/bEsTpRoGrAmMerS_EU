package view.screenObjects;

import com.badlogic.gdx.graphics.Texture;
import run.Boot;
import view.IRender;

/**
 * Screen object that renders Sprites on the screen.
 * @author Lukas Jigberg
 */

public class Sprite implements IRender {

    private final Boot game;
    private int xPos;
    private int yPos;
    private final int width;
    private final int height;
    private final Texture puckemon;

    /**
     * Constructor for Sprite
     *
     * @param game     the game.
     * @param xPos     the x-position where to print.
     * @param yPos     the y-position where to print.
     * @param width    the width of the sprite.
     * @param height   the height of the sprite.
     * @param puckemon the puckemon.
     */
    public Sprite(Boot game, int xPos, int yPos, int width, int height, Texture puckemon) {
        this.game = game;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.puckemon = puckemon;
    }

    @Override
    public void render() {
        game.batch.draw(puckemon, xPos, yPos, width, height);
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