package view.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import model.entities.Puckemon;

/**
 * A class for animating heals that happens during combat.
 */
public class HealAnimation implements Animable{

    private int heal;
    private Puckemon healReciver;
    private int animationTicks = 100;
    private int posX,posY;

    private BitmapFont font;


    public HealAnimation(int heal, int posX, int posY){
        this.heal = heal;
        this.posX = posX;
        this.posY = posY;
        font = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"), Gdx.files.internal("fonts/pixelfont.png"), false);
        font.getData().setScale(0.75f);


    }

    @Override
    public void render(SpriteBatch batch) {
        animationTicks--;
        batch.begin();
        font.setColor(0,0.7f,0,(float) animationTicks/100);
        font.draw(batch, heal + " DMG", posX+(100-animationTicks)/10, posY);
        batch.end();
    }

    @Override
    public boolean isDone() {
        return (animationTicks <= 0);
    }
}
