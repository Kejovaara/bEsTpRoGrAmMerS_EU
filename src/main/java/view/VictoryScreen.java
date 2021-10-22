package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import run.Boot;
import view.screenObjects.TextBox;

/**
 * Shows the Victory screen and gives the result of battle, experience given and possible evolution.
 * @author Lukas Jigberg
 */
public class VictoryScreen implements Screen {

    private final Boot game;
    private final Stage stage;

    OrthographicCamera camera;
    Texture menu;

    public VictoryScreen(final Boot game, SpriteBatch batch) {
        this.game = game;
        BitmapFont desFont = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"), Gdx.files.internal("fonts/pixelfont.png"), false);
        TextBox textBox = new TextBox(desFont, batch, Color.BLACK, 30, 640 - 100, 380, 140, true, battleReport(), 0.75f);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        menu = new Texture(Gdx.files.internal("Victory.png"));
        stage = new Stage();
        stage.addActor(textBox.getLabel());
    }

    private String battleReport(){
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaa";
        return s;
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(menu, 0, 0, this.camera.viewportWidth, this.camera.viewportHeight);
        game.batch.end();

        stage.draw();

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
