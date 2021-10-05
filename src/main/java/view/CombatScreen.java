package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import model.Model;
import model.entities.Puckemon;
import run.Boot;

public class CombatScreen implements Screen {

    final Boot game;
    private Model model;
    private int screenWidth, screenHeight;

    OrthographicCamera camera;
    Texture pucke1,pucke2, background;

    public CombatScreen(final Boot game, Model model) {
        this.game = game;
        this.model = model;
        this.screenWidth = game.getScreenWidth();
        this.screenHeight = game.getScreenHeight();

        Puckemon playerPuckemon = model.getPlayerPuckemon();
        Puckemon trainerPuckemon = model.getTrainerPuckemon();

//        int playerID = playerPuckemon.getID();q

        camera = new OrthographicCamera();
        camera.setToOrtho(false);

        pucke1 = new Texture(Gdx.files.internal("PuckemonFront/2.png"));
        pucke2 = new Texture(Gdx.files.internal("PuckemonBack/1.png"));
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
