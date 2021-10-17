package view.screenObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import view.IDraw;
import view.IRender;

import java.util.List;

public class RectangleBorder implements IDraw , IRender {

    private ShapeRenderer shapeRenderer;
    private int xPos;
    private int yPos;
    private int width;
    private int height;
    private Color outerColor;
    private Color innerColor;
    private int lineWidth;


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

    public void render(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(outerColor);
        shapeRenderer.rect( xPos,yPos,width,height);
        shapeRenderer.setColor(innerColor);
        shapeRenderer.rect( xPos+Math.round(lineWidth/2),yPos+Math.round(lineWidth/2),width-lineWidth,height-lineWidth);
        shapeRenderer.end();
    }

    @Override
    public void draw() {
        render();
    }

}