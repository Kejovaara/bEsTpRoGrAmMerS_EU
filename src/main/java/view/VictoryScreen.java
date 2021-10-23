package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import view.screenObjects.TextBox;

/**
 * Shows the Victory screen and gives the result of battle, experience gained.
 * @author Lukas Jigberg
 */
public class VictoryScreen implements Screen {

    private final SpriteBatch batch;
    private final TextBox textBox;

    private final OrthographicCamera camera;
    private final Texture menu;

    /**
     * Constructor for class.
     * @param batch used to render objects.
     */
    public VictoryScreen(SpriteBatch batch) {
        this.batch = batch;
        BitmapFont desFont = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"), Gdx.files.internal("fonts/pixelfont.png"), false);
        textBox = new TextBox(desFont, batch, Color.BLACK, 30, 640-120, 380, 140, true, "Your Puckemon gained 1000 experience each.", 0.75f);

        camera = new OrthographicCamera();
        camera.setToOrtho(false);

        menu = new Texture(Gdx.files.internal("Victory.png"));
    }

    /**
     * Renders the background and the textbox.
     * @param delta LibGdx needed variable for frame updating.
     */
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(menu, 0, 0, this.camera.viewportWidth, this.camera.viewportHeight);
        textBox.render();
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
