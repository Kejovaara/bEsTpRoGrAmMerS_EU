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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class InventoryScreen implements Screen {

    final Boot game;
    private Model model;

    private int screenWidth, screenHeight;
    private ShapeRenderer shapeRenderer;
    private Stage stage;
    private boolean isActive = false;
    private int targetIndex = 0;
    private List<ListItem> listItems;
    private List<Item> inventory;
    ListItem listItem;

    private BitmapFont inventoryFont, inventoryTitleFont,inventoryItemTitle, backFont;
    OrthographicCamera camera;
    Texture descriptionBox, background;

    Label.LabelStyle fontStyle = new Label.LabelStyle();
    Label.LabelStyle titleStyle = new Label.LabelStyle();
    Label.LabelStyle itemTitleStyle = new Label.LabelStyle();
    Label.LabelStyle backStyle = new Label.LabelStyle();



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

        // FONT SETTINGS
        inventoryFont = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"));
        inventoryFont.getData().setScale(0.75f);
        backFont = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"));
        backFont.getData().setScale(0.75f);
        inventoryTitleFont = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"));
        inventoryTitleFont.getData().setScale(1.25f);
        inventoryItemTitle = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"));
        inventoryItemTitle.getData().setScale(1f);

        //FONT STYLING
        fontStyle.font = inventoryFont;
        fontStyle.fontColor = Color.BLACK;
        titleStyle.font = inventoryTitleFont;
        titleStyle.fontColor = Color.BLACK;
        itemTitleStyle.font = inventoryItemTitle;
        itemTitleStyle.fontColor = Color.BLACK;
        backStyle.font = backFont;



        itemTitle = new Label("Default Title", itemTitleStyle);
        itemTitle.setSize(380,50);
        itemTitle.setPosition(520,490);

        itemDescription = new Label("Default description",fontStyle);
        itemDescription.setSize(380,140);
        itemDescription.setWrap(true);
        itemDescription.setPosition(520,360);



        renderItems();
        //updateDescription(inventory.get(0));

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
        stage.addActor(itemAmountLabel);*/
/*
        //Item description
        Label itemDescriptionLabel = new Label("Small Healing Potion", fontStyle);
        itemDescriptionLabel.setSize(200, 10);
        itemDescriptionLabel.setPosition(600,500);
        itemDescriptionLabel.setWrap(true);
        stage.addActor(itemDescriptionLabel); */



        background = new Texture(Gdx.files.internal("inventory_background.png"));
        descriptionBox = new Texture(Gdx.files.internal("inventory_description_box.png"));

    }

    public void renderItems(){
        int y = 500;
        int listItemDistance = 55;

        inventory = model.getInventory();
        listItems = new ArrayList<>();


        int i = 0;
        stage.clear();
        stage.addActor(itemTitle);
        stage.addActor(itemDescription);
            //BACK BUTTON
            Label backButtonLabel = new Label("PRESS SPACE TO GO BACK", fontStyle);
            backButtonLabel.setSize(75,10);
            backButtonLabel.setPosition(100,50);
            backButtonLabel.setWrap(false);
            stage.addActor(backButtonLabel);
        for(Item item : inventory){
            listItem = new ListItem(item, fontStyle,y);
            if(targetIndex == i){
                listItem.setActive();
            }
            listItems.add(listItem);
            y -= listItemDistance;
            stage.addActor(listItem.getListItemBackground());
            stage.addActor(listItem.getItemImage());
            stage.addActor(listItem.getItemLabel());
            stage.addActor(listItem.getItemAmount());
            i++;
        }
        listItemSelector(targetIndex);
    }

    public void moveUp(){
        if(targetIndex == 0){
            targetIndex = inventory.size()-1;
        }else{
            targetIndex--;
        }
    }

    public void moveDown(){
        if(targetIndex == inventory.size()-1){
            targetIndex = 0;
        }else{
            targetIndex++;
        }
    }

    public void updateDescription(Item item){
        itemTitle.setText(item.getName());
        itemDescription.setText(item.getDescription());
    }

    private void listItemSelector(int index){
        if(targetIndex == index){
            listItem.setActive();
            updateDescription(inventory.get(index));
        }else{
            listItem.setInactive();
        }
    }

    public int getTargetIndex(){
        return targetIndex;
    }


    @Override
    public void render(float v) {
        ScreenUtils.clear(	0.906f, 0.965f, 0.984f,1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
            game.batch.draw(background, 0, 0, this.camera.viewportWidth,this.camera.viewportHeight);
            game.batch.draw(descriptionBox, 500,320,407,220);
        game.batch.end();

        renderItems();
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
