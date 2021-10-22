package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import model.Model;
import run.Boot;
import view.menu.Menu;
import view.menu.MenuBuilder;


import java.util.List;

/**
 * Screen that present the inventory in the game.
 * @author André Kejovaara
 */
public class InventoryScreen implements Screen,IView,MessageObserver{

    final Boot game;
    private Model model;

    private int screenWidth, screenHeight;
    private ShapeRenderer shapeRenderer;
    private Stage stage;

    private Label topLabel;
    private Label label;

    private BitmapFont inventoryTitleFont;
    private BitmapFont inventoryFont;
    OrthographicCamera camera;
    Texture descriptionBox, background;

    Label.LabelStyle titleStyle = new Label.LabelStyle();

    Menu menu;

    /**
     * Constructor for Inventory Screen
     * @param game the game
     * @param model the model
     */
    public InventoryScreen(final Boot game, Model model){
        this.game = game;
        this.model = model;
        this.screenHeight = game.getScreenHeight();
        this.screenWidth = game.getScreenWidth();

        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        stage = new Stage();

        shapeRenderer = new ShapeRenderer();

        menu = MenuBuilder.getInventoryMenu(game.batch, game, this, model);

        // FONT SETTINGS
        inventoryTitleFont = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"));
        inventoryTitleFont.getData().setScale(1.25f);
        inventoryFont =new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"));
        inventoryFont.getData().setScale(0.75f);

        //FONT STYLING
        titleStyle.font = inventoryTitleFont;
        titleStyle.fontColor = Color.BLACK;

        Label.LabelStyle fontStyle = new Label.LabelStyle();
        fontStyle.font = inventoryFont;
        fontStyle.fontColor = Color.BLACK;

        //Page Title
        Label titleLabel = new Label("INVENTORY", titleStyle);
        titleLabel.setSize(300, 30);
        titleLabel.setPosition(350,570);
        titleLabel.setAlignment(Align.center);
        titleLabel.setWrap(false);
        stage.addActor(titleLabel);

        topLabel = new Label("",fontStyle);
        topLabel.setSize(520,10);
        topLabel.setPosition(30,80);
        topLabel.setWrap(true);
        stage.addActor(topLabel);

        background = new Texture(Gdx.files.internal("inventory_background.png"));
        descriptionBox = new Texture(Gdx.files.internal("inventory_description_box.png"));

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(	0.906f, 0.965f, 0.984f,1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
            game.batch.draw(background, 0, 0, this.camera.viewportWidth,this.camera.viewportHeight);
            game.batch.draw(descriptionBox, 500,307,407,220);
        game.batch.end();

        stage.act();
        stage.draw();

        menu.render();
    }


    @Override
    public void show() {
        menu = MenuBuilder.getInventoryMenu(game.batch, game,this,model);
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
        stage.dispose();
        shapeRenderer.dispose();
    }

    @Override
    public void switchMenu(int index) {

    }

    @Override
    public void SetMessage(String message) {

    }
}
