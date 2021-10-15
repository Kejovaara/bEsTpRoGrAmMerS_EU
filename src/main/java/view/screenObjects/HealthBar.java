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
    private float maxHealth;
    private float health;


    public HealthBar(int xPos, int yPos, int width, int height, int maxHealth, int health){
        shapeRenderer = new ShapeRenderer();
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.maxHealth = maxHealth;
        this.health = health;
    }

    private void healthColor(){
        shapeRenderer.setColor(0.698f, 1, 0.729f,1);
        if (health/maxHealth > 0.5){
            shapeRenderer.setColor(1f, 238/255f, 85/255f,1);
        }
        if (health/maxHealth > 0.1){
            shapeRenderer.setColor(170/255f, 40/255f, 40/255f,1);
        }

    }

    private void render() {
        shapeRenderer.setColor(57/255f, 57/255f, 57/255f,1);
        shapeRenderer.rect(xPos, yPos, width, height);
        healthColor();
        shapeRenderer.rect(xPos + 2, yPos + 2, (health/maxHealth)*(width-4), height - 4);
    }

    @Override
    public void draw() {
        render();
    }

}