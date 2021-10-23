package view.screenObjects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import view.IRender;

import java.util.List;

/**
 * Screen object that renders rectangles on the screen.
 * @author Lukas Jigberg
 */

public class Rectangle implements IRender{

    private final ShapeRenderer shapeRenderer;
    private int xPos;
    private int yPos;
    private final int width;
    private final int height;
    private final List<Float> color;

    /**
     * Constructor for Rectangle.
     * @param xPos the x-position where to print.
     * @param yPos the y-position where to print.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     * @param color the color of the rectangle.
     */
    public Rectangle(int xPos, int yPos, int width, int height, List<Float> color) {
        shapeRenderer = new ShapeRenderer();
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * Renders the rectangle.
     */
    @Override
    public void render(){
        shapeRenderer.setColor(color.get(0), color.get(1), color.get(2),1);
        shapeRenderer.rect( xPos,yPos,width,height);
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
