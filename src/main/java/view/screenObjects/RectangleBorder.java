package view.screenObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import view.IRender;

/**
 * Screen object that renders a rectangle with borders on the screen.
 * @author Lukas Jigberg
 */

public class RectangleBorder implements IRender {

    private final ShapeRenderer shapeRenderer;
    private int xPos;
    private int yPos;
    private final int width;
    private final int height;
    private final Color outerColor;
    private final Color innerColor;
    private final int lineWidth;

    /**
     * Constructor for Rectangle border.
     * @param xPos the x-position where to print.
     * @param yPos the y-position where to print.
     * @param width the width of the rectangle border.
     * @param height the height of the rectangle border.
     * @param outerColor the border color of the rectangle.
     * @param innerColor the inner color of the rectangle.
     * @param lineWidth the width of the border.
     */
    public RectangleBorder(int xPos, int yPos, int width, int height, Color outerColor, Color innerColor, int lineWidth) {
        shapeRenderer = new ShapeRenderer();
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.outerColor = outerColor;
        this.innerColor = innerColor;
        this.lineWidth = lineWidth;
    }

    @Override
    public void render(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(outerColor);
        shapeRenderer.rect( xPos,yPos,width,height);
        shapeRenderer.setColor(innerColor);
        shapeRenderer.rect( xPos+Math.round((float)lineWidth/2),yPos+Math.round((float)lineWidth/2),width-lineWidth,height-lineWidth);
        shapeRenderer.end();
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