package view.animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * An interface to be able to animate an object in the view.
 */
public interface Animable {
    public void render(SpriteBatch batch);
    public boolean isDone();
}
