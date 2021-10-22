package view.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Class that animates buffs in the game.
 * @author Rasmus Almryd
 */
public class BuffAnimation implements Animable{

    private int buff;
    private String buffType;
    private int animationTicks = 100;
    private int posX, posY;

    private BitmapFont font;

    /**
     * Constructor of BuffAnimation
     * @param buff
     * @param buffType
     * @param posX
     * @param posY
     */
    public BuffAnimation(int buff, String buffType, int posX, int posY) {
        this.buff = buff;
        this.buffType = buffType;
        this.posX = posX;
        this.posY = posY;

        font = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"), Gdx.files.internal("fonts/pixelfont.png"), false);
        font.getData().setScale(0.75f);

    }

    @Override
    public void render(SpriteBatch batch) {
        animationTicks--;
        batch.begin();
        font.setColor(170f/255f,169f/255f,173f/255f,(float) animationTicks/100);
        font.draw(batch, buff + " " + buffType, posX+(100-animationTicks)/10, posY);
        batch.end();
    }

    @Override
    public boolean isDone() {
        return (animationTicks <= 0);
    }
}
