package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import run.Boot;

public class CombatScreen implements Screen {

    final Boot game;
    private int screenWidth, screenHeight;

    OrthographicCamera camera;
    Texture pucke1,pucke2;

    public CombatScreen(final Boot game) {
        this.game = game;
        this.screenWidth = game.getScreenWidth();
        this.screenHeight = game.getScreenHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        pucke1 = new Texture(Gdx.files.internal("2.png"));
        pucke2 = new Texture(Gdx.files.internal("26.png"));


        game.batch.begin();
        game.font.draw(game.batch, "Welcome to Combat!!! ", 100, 250);
        game.font.draw(game.batch, "Tap anywhere to begin!", 100, 200);
        game.batch.draw(pucke1, 400, 350, 128, 128);
        game.batch.draw(pucke2, 200, 0, 128, 128);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            //game.setScreen(new MainMenuScreen(game));
            //dispose();
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
