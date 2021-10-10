package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import model.Model;
import model.inventories.Item;
import model.inventories.ListItem;
import run.Boot;

import java.util.List;

public class InventoryScreen implements Screen {

    final Boot game;
    private Model model;
    private List<Item> items;
    private int screenWidth, screenHeight;
    private ShapeRenderer shapeRenderer;
    private Stage stage;

    private BitmapFont inventoryFont, inventoryTitleFont,inventoryItemTitle;

    OrthographicCamera camera;
    Texture descriptionBox, background;

    Label.LabelStyle fontStyle = new Label.LabelStyle();
    Label.LabelStyle titleStyle = new Label.LabelStyle();
    Label.LabelStyle itemTitleStyle = new Label.LabelStyle();

    Label itemTitle;
    Label itemDescription;

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


        fontStyle.font = inventoryFont;
        fontStyle.fontColor = Color.BLACK;
        titleStyle.font = inventoryTitleFont;
        titleStyle.fontColor = Color.BLACK;
        itemTitleStyle.font = inventoryItemTitle;
        itemTitleStyle.fontColor = Color.BLACK;


        List<Item> inventory = model.getInventory();

        int y = 500;
        int listItemDistance = 47;
        for(Item item : inventory){
            ListItem listItem = new ListItem(item, fontStyle,y);
            y -= listItemDistance;

            stage.addActor(listItem.getItemImage());
            stage.addActor(listItem.getItemLabel());
            stage.addActor(listItem.getItemAmount());
        }

        itemTitle = new Label("Default Title", itemTitleStyle);
        itemTitle.setSize(380,50);
        itemTitle.setPosition(520,480);

        itemDescription = new Label("Default description",fontStyle);
        itemDescription.setSize(380,140);
        itemDescription.setWrap(true);
        itemDescription.setPosition(520,350);

        stage.addActor(itemTitle);
        stage.addActor(itemDescription);

        updateDescription(inventory.get(0));

        //Page Title
        Label titleLabel = new Label("INVENTORY", titleStyle);
        titleLabel.setSize(300, 30);
        titleLabel.setPosition(350,570);
        titleLabel.setAlignment(Align.center);
        titleLabel.setWrap(false);
        stage.addActor(titleLabel);
/*
        //Item title
        Label itemTitleLabel = new Label("ITEM TITLE", titleStyle);
        itemTitleLabel.setSize(300, 30);
        itemTitleLabel.setPosition(350,570);
        itemTitleLabel.setWrap(false);
        stage.addActor(itemTitleLabel);*/
/*
        //Item list
        Label itemListLabel = new Label("ITEM LIST", fontStyle);
        itemListLabel.setSize(520, 10);
        itemListLabel.setPosition(100,500);
        itemListLabel.setWrap(true);
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
        stage.addActor(itemAmountLabel);*/
/*
        //Item description
        Label itemDescriptionLabel = new Label("Small Healing Potion", fontStyle);
        itemDescriptionLabel.setSize(200, 10);
        itemDescriptionLabel.setPosition(600,500);
        itemDescriptionLabel.setWrap(true);
        stage.addActor(itemDescriptionLabel); */

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

    public void updateDescription(Item item){
        itemTitle.setText(item.getName());
        itemDescription.setText(item.getDescription());
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(	0.906f, 0.965f, 0.984f,1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
            game.batch.draw(background, 0, 0, this.camera.viewportWidth,this.camera.viewportHeight);
            game.batch.draw(descriptionBox, 500,310,407,220);
        game.batch.end();

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
