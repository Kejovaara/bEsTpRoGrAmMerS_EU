package view.screenObjects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import view.IDraw;

import java.util.List;

public class HealthBar implements IDraw {

    private final ShapeRenderer shapeRenderer;
    private int xPos;
    private int yPos;
    private int width;
    private int height;
    private List<Float> outerColor;
    private float maxHealth;
    private float health;


    public HealthBar(int xPos, int yPos, int width, int height, List<Float> outerColor, int maxHealth, int health){
        shapeRenderer = new ShapeRenderer();
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.outerColor = outerColor;
        this.maxHealth = maxHealth;
        this.health = health;
    }

    private void render() {
        shapeRenderer.setColor(outerColor.get(0), outerColor.get(1), outerColor.get(2), 1);
        shapeRenderer.rect(xPos, yPos, width, height);
        shapeRenderer.setColor(0.698f, 1, 0.729f,1);
        shapeRenderer.rect(xPos + 2, yPos + 2, (health/maxHealth)*(width-4), height - 4);
    }

    @Override
    public void draw() {
        render();
    }

}