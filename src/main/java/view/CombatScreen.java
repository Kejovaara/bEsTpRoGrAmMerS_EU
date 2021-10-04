package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import run.Boot;

public class CombatScreen implements Screen {

    final Boot game;

    OrthographicCamera camera;
    Texture BigDragonImage;

    public CombatScreen(final Boot game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        BigDragonImage = new Texture(Gdx.files.internal("sprites/BigDragon.gif"));


        game.batch.begin();
        game.font.draw(game.batch, "Welcome to Combat!!! ", 100, 150);
        game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
        game.batch.draw(BigDragonImage, 20, 20, 64, 64);

        game.batch.end();

        if (Gdx.input.isTouched()) {
//            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
