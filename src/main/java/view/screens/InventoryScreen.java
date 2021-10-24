package view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;
import model.Model;
import run.Boot;
import run.VCHandler;
import view.IView;
import view.menu.Menu;
import view.menu.MenuBuilder;
import view.screenObjects.Text;


/**
 * Screen that present the inventory in the game.
 * @author André Kejovaara
 */
public class InventoryScreen implements Screen, IView {

    private final VCHandler handler;
    private final SpriteBatch batch;
    private final Model model;
    private Menu menu;
    private final ShapeRenderer shapeRenderer;

    private final OrthographicCamera camera;
    private final Texture descriptionBox, background;

    private final Text title;

    /**
     * Constructor for Inventory Screen
     * @param handler used by the controllers to switch controller and/or screen.
     * @param model the model
     */
    public InventoryScreen(VCHandler handler, SpriteBatch batch, Model model){
        this.handler = handler;
        this.batch = batch;
        this.model = model;

        camera = new OrthographicCamera();
        camera.setToOrtho(false);

        shapeRenderer = new ShapeRenderer();

        menu = MenuBuilder.getInventoryMenu(batch, handler, model);

        // FONT SETTINGS
        BitmapFont inventoryTitleFont = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"));
        inventoryTitleFont.getData().setScale(1.25f);

        //Page Title
        title = new Text(inventoryTitleFont, batch, Color.BLACK,350,580,"INVENTORY", 1.25f);

        background = new Texture(Gdx.files.internal("inventory_background.png"));
        descriptionBox = new Texture(Gdx.files.internal("inventory_description_box.png"));

    }

    /**
     * Renders the inventory background and textbox.
     * @param delta LibGdx needed variable for frame updating.
     */
    @Override
    public void render(float delta) {
        ScreenUtils.clear(	0.906f, 0.965f, 0.984f,1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
            batch.draw(background, 0, 0, this.camera.viewportWidth,this.camera.viewportHeight);
            batch.draw(descriptionBox, 500,307,407,220);
            title.render();
        batch.end();

        menu.render();
    }

    /**
     * Updates the menu with the current state of the inventory each time this view is displayed.
     */
    @Override
    public void show() {
        menu = MenuBuilder.getInventoryMenu(batch, handler ,model);
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
        shapeRenderer.dispose();
    }

    @Override
    public void switchMenu(int index) {

    }
}
