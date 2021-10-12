package view.screenObjects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import view.IDraw;

import java.util.List;

public class RectangleBorder implements IDraw {

    private final ShapeRenderer shapeRenderer;
    private int xPos;
    private int yPos;
    private int width;
    private int height;
    private List<Float> outerColor;
    private List<Float> innerColor;


    public RectangleBorder(int xPos, int yPos, int width, int height, List<Float> outerColor, List<Float> innerColor) {
        shapeRenderer = new ShapeRenderer();
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.outerColor = outerColor;
        this.innerColor = innerColor;
    }

    private void render(){
        shapeRenderer.setColor(outerColor.get(0), outerColor.get(1), outerColor.get(2),1);
        shapeRenderer.rect( xPos,yPos,width,height);
        shapeRenderer.setColor(innerColor.get(0), innerColor.get(1), innerColor.get(2),1);
        shapeRenderer.rect( xPos+2,yPos+2,width-4,height-4);
    }

    @Override
    public void draw() {
        render();
    }

}