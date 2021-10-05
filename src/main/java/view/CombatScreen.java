package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import model.Model;
import model.entities.Puckemon;
import run.Boot;

public class CombatScreen implements Screen {

    final Boot game;
    private Model model;
    private int screenWidth, screenHeight;
    private ShapeRenderer shapeRenderer;

    private BitmapFont MenuFont;

    OrthographicCamera camera;
    Texture playerPuck,trainerPuck, background;

    public CombatScreen(final Boot game, Model model) {
        this.game = game;
        this.model = model;
        this.screenWidth = game.getScreenWidth();
        this.screenHeight = game.getScreenHeight();

        shapeRenderer = new ShapeRenderer();

        MenuFont = new BitmapFont(Gdx.files.internal("MenuFont.fnt"), Gdx.files.internal("MenuFont.png"),false);
        MenuFont.getData().setScale(0.5f);

        Puckemon playerPuckemon = model.getPlayerPuckemon();
        Puckemon trainerPuckemon = model.getTrainerPuckemon();

        camera = new OrthographicCamera();
        camera.setToOrtho(false);

        playerPuck = getTexture(playerPuckemon.getId(), false);
        trainerPuck = getTexture(trainerPuckemon.getId(), true);
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
        ScreenUtils.clear(	0.906f, 0.965f, 0.984f,1);

        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);
        game.batch.setProjectionMatrix(camera.combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.8f,0.8f,0.8f,1);
        shapeRenderer.ellipse(520,400, 280, 60);
        shapeRenderer.end();

        game.batch.begin();
        game.batch.draw(trainerPuck, 570, 400, 192, 192);
        game.batch.draw(playerPuck, 200, 110, 256, 256);
        //game.batch.draw(background, 0, 0, this.camera.viewportWidth, this.camera.viewportHeight);
        game.batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.8f,0.8f,0.8f,1);
        shapeRenderer.rect(0,0,960, 180);
        shapeRenderer.setColor(1,1,1,1);
        shapeRenderer.rect(560,0,400, 180);
        shapeRenderer.end();

        Gdx.gl.glLineWidth(4);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0,0,0,1);
        shapeRenderer.rect(0,0,960, 180);
        shapeRenderer.end();

        Gdx.gl.glLineWidth(6);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(560,0,400, 180);
        shapeRenderer.end();

        game.batch.begin();
        MenuFont.draw(game.batch, "Attack", 600, 140);
        MenuFont.draw(game.batch, "Switch", 600, 60);
        MenuFont.draw(game.batch, "Inventory", 800, 140);
        MenuFont.draw(game.batch, "Flee", 800, 60);
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
