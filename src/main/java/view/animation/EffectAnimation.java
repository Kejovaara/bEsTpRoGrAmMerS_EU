package view.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * A class that animates effects in the game as they happen in combat.
 * @author Rasmus Almryd
 */

public class EffectAnimation implements Animable{

    private int effectPower;
    private int posX,posY;
    private String effectUnit;
    private int animationDuration;

    private Color color;
    private BitmapFont font;

    /**
     * Constructor for EffectAnimation
     * @param effectPower
     * @param posX
     * @param posY
     * @param effectUnit
     */
    public EffectAnimation(int effectPower, int posX, int posY, String effectUnit){
        this(effectPower,posX,posY,effectUnit, 200, new Color(170f/255f,169f/255f,173f/255f,1));
    }

    /**
     * Constructor for EffectAnimation
     * @param effectPower
     * @param posX
     * @param posY
     * @param effectUnit
     * @param color
     */
    public EffectAnimation(int effectPower, int posX, int posY, String effectUnit, Color color){
        this(effectPower,posX,posY,effectUnit, 200, color);
    }

    /**
     * Default constructor for EffectAnimation with duration and colors.
     * @param effectPower
     * @param posX
     * @param posY
     * @param effectUnit
     * @param animationDuration
     * @param color
     */
    public EffectAnimation(int effectPower, int posX, int posY, String effectUnit, int animationDuration, Color color) {
        this.effectPower = effectPower;
        this.posX = posX;
        this.posY = posY;
        this.effectUnit = effectUnit;
        this.animationDuration = animationDuration;
        this.color = color;

        font = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"), Gdx.files.internal("fonts/pixelfont.png"), false);
        font.getData().setScale(0.75f);
    }



    @Override
    public void render(SpriteBatch batch) {
        animationDuration--;
        batch.begin();
        font.setColor(color.r,color.g,color.b,(float) animationDuration/100);
        font.draw(batch, effectPower + " " + effectUnit, posX+(100-animationDuration)/10, posY);
        batch.end();
    }

    @Override
    public boolean isDone() {
        return (animationDuration<=0);
    }
}
