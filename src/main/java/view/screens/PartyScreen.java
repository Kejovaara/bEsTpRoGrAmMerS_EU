package view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import model.Model;
import run.Boot;
import run.VCHandler;
import view.IView;
import view.menu.Menu;
import view.menu.MenuBuilder;

/**
 * Creates the screen for the Players Party. It has a static, non interactable objects in the background and textbox.
 * @author Rasmus Almryd
 * @author Lukas Jigberg
 */
public class PartyScreen implements Screen, IView {

    private final VCHandler handler;
    private final SpriteBatch batch;
    private final Model model;

    OrthographicCamera camera;
    Texture background;

    private Menu menu;

    /**
     * Constructor of PartyScreen.
     * @param handler used by the controllers to switch controller and/or screen.
     * @param batch This is used to display IRender objects
     * @param model used to display the party.
     */
    public PartyScreen(VCHandler handler, SpriteBatch batch, Model model) {
        this.batch = batch;
        this.handler = handler;
        this.model = model;

        background = new Texture(Gdx.files.internal("PartyBackground.png"));
        menu = MenuBuilder.getPartyMenu(batch, handler, model);

        camera = new OrthographicCamera();
        camera.setToOrtho(false);
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
        batch.draw(background, 0, 0, this.camera.viewportWidth, this.camera.viewportHeight);
        batch.end();

        menu.render();
    }

    /**
     * Updates the menu with the current state of the party each time this view is displayed.
     */
    @Override
    public void show() {
        menu = MenuBuilder.getPartyMenu(batch,handler, model);
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

    @Override
    public void switchMenu(int index) {

    }
}