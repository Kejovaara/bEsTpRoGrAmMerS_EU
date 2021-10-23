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

    private final int effectPower;
    private final int posX,posY;
    private final String effectUnit;
    private int animationDuration;

    private final Color color;
    private final BitmapFont font;

    /**
     * Constructor for EffectAnimation.
     * @param effectPower The amount to display ("235" as an example).
     * @param posX the position in x.
     * @param posY the position in y.
     * @param effectUnit the unit to display after effectPower ("DMG" as an example).
     */
    public EffectAnimation(int effectPower, int posX, int posY, String effectUnit){
        this(effectPower,posX,posY,effectUnit, 200, new Color(170f/255f,169f/255f,173f/255f,1));
    }

    /**
     * Constructor for EffectAnimation.
     * @param effectPower The amount to display ("235" as an example).
     * @param posX the position in x.
     * @param posY the position in y.
     * @param effectUnit the unit to display after effectPower ("DMG" as an example).
     * @param color  the color of the effect text.
     */
    public EffectAnimation(int effectPower, int posX, int posY, String effectUnit, Color color){
        this(effectPower,posX,posY,effectUnit, 200, color);
    }

    /**
     * Constructor for EffectAnimation.
     * @param effectPower The amount to display. ("235" as an example).
     * @param posX the position in x.
     * @param posY the position in y.
     * @param effectUnit the unit to display after effectPower. ("DMG" as an example).
     * @param animationDuration the duration of the animation.
     * @param color the color of the effect text.
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

    /**
     * Renders the effect.
     * @param batch used to render the objects.
     */
    @Override
    public void render(SpriteBatch batch) {
        animationDuration--;
        batch.begin();
        font.setColor(color.r,color.g,color.b,(float) animationDuration/100);
        font.draw(batch, effectPower + " " + effectUnit, posX+(float)(100-animationDuration)/10, posY);
        batch.end();
    }

    /**
     * @return if the animation is done, based on the duration.
     */
    @Override
    public boolean isDone() {
        return (animationDuration<=0);
    }
}
