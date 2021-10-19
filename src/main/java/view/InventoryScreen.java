package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import model.Model;
import model.inventories.Item;
import model.inventories.ListItem;
import run.Boot;
import view.menu.Menu;
import view.menu.MenuFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class InventoryScreen implements Screen,IView{

    final Boot game;
    private Model model;

    private int screenWidth, screenHeight;
    private ShapeRenderer shapeRenderer;
    private ScrollPane scrollPane;
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
    Label inventoryTitle;

    Menu menu;

    public InventoryScreen(final Boot game, Model model){
        this.game = game;
        this.model = model;
        this.screenHeight = game.getScreenHeight();
        this.screenWidth = game.getScreenWidth();

        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        stage = new Stage();

        shapeRenderer = new ShapeRenderer();

        menu = MenuFactory.getInventoryMenu(game, this,model);

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


        //Page Title
        Label titleLabel = new Label("INVENTORY", titleStyle);
        titleLabel.setSize(300, 30);
        titleLabel.setPosition(350,570);
        titleLabel.setAlignment(Align.center);
        titleLabel.setWrap(false);
        stage.addActor(titleLabel);

        background = new Texture(Gdx.files.internal("inventory_background.png"));
        descriptionBox = new Texture(Gdx.files.internal("inventory_description_box.png"));

    }

    public void renderItems(){
        int y = 500;
        int x = 100;
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
            updateDescriptionBox(inventory.get(index));
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
            game.batch.draw(descriptionBox, 500,307,407,220);
        game.batch.end();

        renderItems();
        stage.act();
        stage.draw();

        menu.render();
    }


    @Override
    public void show() {
        menu = MenuFactory.getInventoryMenu(game, this,model);
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
}
