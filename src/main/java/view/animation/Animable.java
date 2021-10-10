package view.animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Animable {
    public void render(SpriteBatch batch);
    public boolean isDone();
}
