package view.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import model.entities.Puckemon;

public class DamageAnimation implements Animable{

    private int damage;
    private int animationTicks = 100;
    private int posX,posY;

    private BitmapFont font;

    public DamageAnimation(int damage, int posX, int posY){
        this.damage = damage;
        this.posX = posX;
        this.posY = posY;
        font = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"), Gdx.files.internal("fonts/pixelfont.png"), false);
        font.getData().setScale(0.75f);
    }

    @Override
    public void render(SpriteBatch batch) {
        animationTicks--;
        batch.begin();
        font.setColor(0.7f,0,0,(float) animationTicks/100);
        font.draw(batch, damage + " DMG", posX+(100-animationTicks)/10, posY);
        batch.end();
        //System.out.println(animationTicks/100);
    }

    @Override
    public boolean isDone() {
        return (animationTicks <= 0);
    }



}
