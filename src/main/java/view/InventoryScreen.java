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

public class InventoryScreen implements Screen {

    final Boot game;
    private Model model;
    private int screenWidth, screenHeight;
    private ShapeRenderer shapeRenderer;
    private Stage stage;

    private BitmapFont inventoryFont, inventoryTitleFont,inventoryItemTitle;

    OrthographicCamera camera;
    Texture descriptionBox, background;

    public InventoryScreen(final Boot game, Model model){
        this.game = game;
        this.model = model;
        this.screenHeight = game.getScreenHeight();
        this.screenWidth = game.getScreenWidth();

        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        stage = new Stage();

        shapeRenderer = new ShapeRenderer();

        inventoryFont = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"));
        inventoryFont.getData().setScale(0.75f);
        inventoryTitleFont = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"));
        inventoryTitleFont.getData().setScale(1.25f);
        inventoryItemTitle = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"));
        inventoryItemTitle.getData().setScale(1f);

        Label.LabelStyle fontStyle = new Label.LabelStyle();
        fontStyle.font = inventoryFont;
        fontStyle.fontColor = Color.BLACK;

        Label.LabelStyle titleStyle = new Label.LabelStyle();
        titleStyle.font = inventoryTitleFont;
        titleStyle.fontColor = Color.BLACK;

        //Page Title
        Label titleLabel = new Label("INVENTORY", titleStyle);
        titleLabel.setSize(300, 30);
        titleLabel.setPosition(350,570);
        titleLabel.setAlignment(Align.center);
        titleLabel.setWrap(false);
        stage.addActor(titleLabel);

        //Item title
        Label itemTitleLabel = new Label("ITEM TITLE", titleStyle);
        itemTitleLabel.setSize(300, 30);
        itemTitleLabel.setPosition(350,570);
        itemTitleLabel.setWrap(false);
        stage.addActor(itemTitleLabel);

        //Item list
        Label itemListLabel = new Label("ITEM LIST", fontStyle);
        itemListLabel.setSize(520, 10);
        itemListLabel.setPosition(100,500);
        itemListLabel.setWrap(false);
        stage.addActor(itemListLabel);

        //Item test
        Label itemTestLabel = new Label("Small Healing Potion", fontStyle);
        itemTestLabel.setSize(520, 10);
        itemTestLabel.setPosition(100,475);
        itemTestLabel.setWrap(false);
        stage.addActor(itemTestLabel);

        //Item quantity
        Label itemAmountLabel = new Label("Item amount", fontStyle);
        itemAmountLabel.setSize(520, 10);
        itemAmountLabel.setPosition(400,475);
        itemAmountLabel.setWrap(false);
        stage.addActor(itemAmountLabel);

        //Item description
        Label itemDescriptionLabel = new Label("Small Healing Potion", fontStyle);
        itemDescriptionLabel.setSize(200, 10);
        itemDescriptionLabel.setPosition(600,500);
        itemDescriptionLabel.setWrap(true);
        stage.addActor(itemDescriptionLabel);

        //BACK BUTTON
        Label backButtonLabel = new Label("Back to combat", fontStyle);
        backButtonLabel.setSize(75,10);
        backButtonLabel.setPosition(100,50);
        backButtonLabel.setWrap(false);
        stage.addActor(backButtonLabel);

        background = new Texture(Gdx.files.internal("inventory_background.png"));
        descriptionBox = new Texture(Gdx.files.internal("inventory_description_box.png"));


    }

    public void renderItems(){

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(	0.906f, 0.965f, 0.984f,1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
            game.batch.draw(background, 0, 0, this.camera.viewportWidth,this.camera.viewportHeight);
            game.batch.draw(descriptionBox, 500,120,407,220);
        game.batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(57/255f, 57/255f, 57/255f,1);
        shapeRenderer.rect( 32,640-(165+57),328,165);
        shapeRenderer.rect(36,640-(161+57),320,157);
        shapeRenderer.end();

        stage.act();
        stage.draw();
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
