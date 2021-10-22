package view.animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * An interface to be able to animate an object in the view.
 */
public interface Animable {
    void render(SpriteBatch batch);
    boolean isDone();
}
