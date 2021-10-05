package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import model.Main;
import run.Boot;

public class CombatScreen implements Screen {

    final Boot game;
    private Main main;
    private int screenWidth, screenHeight;

    OrthographicCamera camera;
    Texture pucke1,pucke2, background;

    public CombatScreen(final Boot game, Main main) {
        this.game = game;
        this.main = main;
        this.screenWidth = game.getScreenWidth();
        this.screenHeight = game.getScreenHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false);

        pucke1 = new Texture(Gdx.files.internal("2.png"));
        pucke2 = new Texture(Gdx.files.internal("26.png"));
        background = new Texture(Gdx.files.internal("Background.png"));
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(background, 0, 0, this.camera.viewportWidth, this.camera.viewportHeight);
        game.batch.draw(pucke1, 600, 400, 128, 128);
        game.batch.draw(pucke2, 200, 167, 128, 128);
        game.batch.end();
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
