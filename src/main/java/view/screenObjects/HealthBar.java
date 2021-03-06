package view.screenObjects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import view.IRender;

/**
 * Screen object that renders health bars on the screen
 * @author Lukas Jigberg
 */

public class HealthBar implements IRender {

    private final ShapeRenderer shapeRenderer;
    private int xPos;
    private int yPos;
    private final int width;
    private final int height;
    private float maxHealth;
    private float health;

    /**
     * Constructor for health bar
     * @param xPos the position where to print
     * @param yPos the position where to print
     * @param width the width of the health bar
     * @param height the height of the health bar
     * @param maxHealth the max health of the puckemon
     * @param health the current health of the puckemon
     */
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
        if (health/maxHealth < 0.2){
            shapeRenderer.setColor(170/255f, 40/255f, 40/255f,1);
        }else if (health/maxHealth < 0.5){
            shapeRenderer.setColor(1f, 238/255f, 85/255f,1);
        }

    }

    /**
     * Sets the health of the health bar
     * @param health the current health
     */
    public void setHealth(int health){
        this.health = health;
    }

    public void setMaxHealth(int maxHealth){
        this.maxHealth = maxHealth;
    }

    /**
     * Renders the health bar. Also sets the color depending on amount of hp left.
     */
    public void render() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(57/255f, 57/255f, 57/255f,1);
        shapeRenderer.rect(xPos, yPos, width, height);
        healthColor();
        shapeRenderer.rect(xPos + 2, yPos + 2, (health/maxHealth)*(width-4), height - 4);
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