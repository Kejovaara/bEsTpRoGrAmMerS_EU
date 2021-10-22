package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import run.Boot;

/**
 * Shows the GameOver screen.
 * @author Lukas Jigberg
 */
public class GameOverScreen implements Screen {

    final Boot game;

    OrthographicCamera camera;
    Texture menu;

    public GameOverScreen(final Boot game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        menu = new Texture(Gdx.files.internal("GameOver.png"));
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(menu, 0, 0, this.camera.viewportWidth, this.camera.viewportHeight);
        game.batch.end();

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
