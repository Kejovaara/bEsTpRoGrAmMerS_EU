package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.ScreenUtils;
import model.Model;
import model.entities.Puckemon;
import run.Boot;

public class CombatScreen implements Screen {

    final Boot game;
    private Model model;
    private int screenWidth, screenHeight;

    OrthographicCamera camera;
    Texture playerPuck,trainerPuck, background;

    public CombatScreen(final Boot game, Model model) {
        this.game = game;
        this.model = model;
        this.screenWidth = game.getScreenWidth();
        this.screenHeight = game.getScreenHeight();

        Puckemon playerPuckemon = model.getPlayerPuckemon();
        Puckemon trainerPuckemon = model.getTrainerPuckemon();

        camera = new OrthographicCamera();
        camera.setToOrtho(false);

        //FONT
        Label.LabelStyle fontStyle = new Label.LabelStyle();
        BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/pixelfont.ttf"));
        fontStyle.font = font;
        fontStyle.fontColor = Color.BLACK;

        Label label = new Label("Test text 123",fontStyle);


        playerPuck = getTexture(playerPuckemon.getId(), false);
        trainerPuck = getTexture(trainerPuckemon.getId(), true);
        //pucke2 = new Texture(Gdx.files.internal("PuckemonBack/1.png"));
        background = new Texture(Gdx.files.internal("Background.png"));
    }

    Texture getTexture(int id, boolean front) {
        if(front) {
            return new Texture(Gdx.files.internal("front/" + id + ".png"));
        }else{
            return new Texture(Gdx.files.internal("back/" + id + ".png"));
        }
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(background, 0, 0, this.camera.viewportWidth, this.camera.viewportHeight);
        game.batch.draw(trainerPuck, 600, 400, 128, 128);
        game.batch.draw(playerPuck, 200, 167, 128, 128);

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
