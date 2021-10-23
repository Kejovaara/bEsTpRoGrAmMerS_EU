package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * Shows the GameOver screen.
 * @author Lukas Jigberg
 */
public class GameOverScreen implements Screen {

    private final Batch batch;
    private final OrthographicCamera camera;
    private final Texture menu;

    /**
     * @param batch used to render to screen.
     */
    public GameOverScreen(Batch batch) {
        this.batch = batch;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        menu = new Texture(Gdx.files.internal("GameOver.png"));
    }

    /**
     * Renders the inventory background and textbox.
     * @param delta LibGdx needed variable for frame updating.
     */
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(menu, 0, 0, this.camera.viewportWidth, this.camera.viewportHeight);
        batch.end();

    }

    @Override
    public void show() {}

    @Override
    public void resize(int i, int i1) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
