package view.animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * An interface to be able to animate an object in the view.
 * @author Rasmus Almryd
 */
public interface Animable {
    /**
     * Renders itself to the screen.
     * @param batch used to render objects.
     */
    void render(SpriteBatch batch);

    /**
     * Checks if animation is complete.
     * @return true if complete, false if not.
     */
    boolean isDone();
}
