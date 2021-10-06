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
import com.badlogic.gdx.utils.ScreenUtils;
import model.Model;
import run.Boot;

public class InventoryScreen implements Screen {

    final Boot game;
    private Model model;
    private int screenWidth, screenHeight;
    private ShapeRenderer shapeRenderer;
    private Stage stage;

    private BitmapFont inventoryFont;

    OrthographicCamera camera;
    Texture itemList, descriptionBox, background;

    public InventoryScreen(final Boot game, Model model){
        this.game = game;
        this.model = model;
        this.screenHeight = game.getScreenHeight();
        this.screenWidth = game.getScreenWidth();

        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        stage = new Stage();

        shapeRenderer = new ShapeRenderer();

        inventoryFont = new BitmapFont(Gdx.files.internal("pixelfont.fnt"));
        inventoryFont.getData().setScale(0.75f);

        Label.LabelStyle fontStyle = new Label.LabelStyle();
        fontStyle.font = inventoryFont;
        fontStyle.fontColor = Color.BLACK;

        //Item list
        Label itemListLabel = new Label("Item List", fontStyle);
        itemListLabel.setSize(520, 10);
        itemListLabel.setPosition(50,500);
        itemListLabel.setWrap(false);
        stage.addActor(itemListLabel);

        //Item description
        Label itemDescriptionLabel = new Label("Item List", fontStyle);
        itemDescriptionLabel.setSize(200, 10);
        itemDescriptionLabel.setPosition(50,500);
        itemDescriptionLabel.setWrap(true);
        stage.addActor(itemDescriptionLabel);

        //BACK BUTTON
        Label backButtonLabel = new Label("Back to combat", fontStyle);
        backButtonLabel.setSize(75,10);
        backButtonLabel.setPosition(50,125);
        backButtonLabel.setWrap(false);
        stage.addActor(backButtonLabel);

        background = new Texture(Gdx.files.internal("inventory_background.png"));


    }


    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(	0.906f, 0.965f, 0.984f,1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);


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
