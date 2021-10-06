package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.ScreenUtils;
import model.Model;
import model.entities.Puckemon;
import run.Boot;

public class CombatScreen implements Screen {

    final Boot game;
    private Model model;
    private int screenWidth, screenHeight;
    private ShapeRenderer shapeRenderer;
    private Stage stage;

    private BitmapFont menuFont;
    private BitmapFont combatFont;
    private BitmapFont statsFont;

    OrthographicCamera camera;
    Texture playerPuck,trainerPuck, background, cursorTexture;

    private Boolean mainCombatMenu = true;
    private int cursorIndex = 0;
    private int cursorX,cursorY;

    private Puckemon playerPuckemon;
    private Puckemon trainerPuckemon;

    public CombatScreen(final Boot game, Model model) {
        this.game = game;
        this.model = model;
        this.screenWidth = game.getScreenWidth();
        this.screenHeight = game.getScreenHeight();

        shapeRenderer = new ShapeRenderer();

        //Menu Font
        menuFont = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"), Gdx.files.internal("fonts/pixelfont.png"), false);
        menuFont.getData().setScale(0.75f);

        statsFont = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"), Gdx.files.internal("fonts/pixelfont.png"), false);
        statsFont.getData().setScale(0.5f);

        playerPuckemon = model.getPlayerPuckemon();
        trainerPuckemon = model.getTrainerPuckemon();

        camera = new OrthographicCamera();
        camera.setToOrtho(false);

        //COMABT BOX TEXT
        stage = new Stage();
        combatFont = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"));;
        combatFont.getData().setScale(0.75f);

        Label.LabelStyle fontStyle = new Label.LabelStyle();
        fontStyle.font = combatFont;
        fontStyle.fontColor = Color.BLACK;

        Label label = new Label("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque nec feugiat.",fontStyle);
        label.setSize(520,10);
        label.setPosition(30,90);
        label.setWrap(true);
        stage.addActor(label);



        playerPuck = getTexture(playerPuckemon.getId(), false);
        trainerPuck = getTexture(trainerPuckemon.getId(), true);
        //pucke2 = new Texture(Gdx.files.internal("PuckemonBack/1.png"));
        background = new Texture(Gdx.files.internal("Background.png"));
        cursorTexture = new Texture(Gdx.files.internal("Arrow.png"));
    }

    Texture getTexture(int id, boolean front) {
        if(front) {
            return new Texture(Gdx.files.internal("front/" + id + ".png"));
        }else{
            return new Texture(Gdx.files.internal("back/" + id + ".png"));
        }
    }



    @Override
    public void render(float delta) {
        ScreenUtils.clear(	0.906f, 0.965f, 0.984f,1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.8f,0.8f,0.8f,1);
        shapeRenderer.ellipse(520,400, 280, 60);
        shapeRenderer.end();

        game.batch.begin();
        game.batch.draw(trainerPuck, 570, 400, 192, 192);
        game.batch.draw(playerPuck, 200, 110, 256, 256);
        //game.batch.draw(background, 0, 0, this.camera.viewportWidth, this.camera.viewportHeight);
        game.batch.end();

        drawPuckeStats();

        if (mainCombatMenu) drawMainCombatMenu();
        else drawCombatAttackMenu();


    }


    private void drawPuckeStats(){


        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1,1,1,1);
        shapeRenderer.rect(40,this.camera.viewportHeight-140, 400, 120);
        shapeRenderer.rect(this.camera.viewportWidth-440,200, 400, 120);

        //HP BARS
        shapeRenderer.setColor(0.7f,0.7f,0.7f,1);
        shapeRenderer.rect(60, this.camera.viewportHeight-100, 360,40);
        shapeRenderer.setColor(0.698f, 1, 0.729f,1);
        shapeRenderer.rect(60, this.camera.viewportHeight-100,((float)trainerPuckemon.getHealth()/trainerPuckemon.getMaxHealth())*360,40);

        shapeRenderer.setColor(0.7f,0.7f,0.7f,1);
        shapeRenderer.rect(this.camera.viewportWidth-420, 240, 360,40);
        shapeRenderer.setColor(0.698f, 1, 0.729f,1);
        shapeRenderer.rect(this.camera.viewportWidth-420, 240, ((float)playerPuckemon.getHealth()/playerPuckemon.getMaxHealth())*360,40);

        shapeRenderer.end();

        Gdx.gl.glLineWidth(4);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0,0,0,1);
        shapeRenderer.rect(40,this.camera.viewportHeight-140, 400, 120);
        shapeRenderer.rect(this.camera.viewportWidth-440,200, 400, 120);
        shapeRenderer.end();



        game.batch.begin();
        statsFont.setColor(0,0,0,1);
        statsFont.draw(game.batch, trainerPuckemon.getName(),60,this.camera.viewportHeight-40);
        statsFont.draw(game.batch, "Lv "+trainerPuckemon.getLevel(),360,this.camera.viewportHeight-40);

        statsFont.draw(game.batch, playerPuckemon.getName(),this.camera.viewportWidth-420,300);
        statsFont.draw(game.batch, "Lv "+playerPuckemon.getLevel(),this.camera.viewportWidth-120,300);
        statsFont.draw(game.batch, playerPuckemon.getHealth() + " / " + playerPuckemon.getMaxHealth(),this.camera.viewportWidth-120,230);


        game.batch.end();
    }

    private void drawMainCombatMenu(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.8f,0.8f,0.8f,1);
        shapeRenderer.rect(0,0,960, 180);
        shapeRenderer.setColor(1,1,1,1);
        shapeRenderer.rect(560,0,400, 180);
        shapeRenderer.end();

        Gdx.gl.glLineWidth(6);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0,0,0,1);
        shapeRenderer.rect(0,0,960, 180);
        shapeRenderer.rect(560,0,400, 180);
        shapeRenderer.end();

        game.batch.begin();
        menuFont.setColor(0,0,0,1);
        calculateCursorPos();
        game.batch.draw(cursorTexture,cursorX, cursorY, 15, 15);
        menuFont.draw(game.batch, "Attack", 600, 140);
        menuFont.draw(game.batch, "Switch", 600, 60);
        menuFont.draw(game.batch, "Inventory", 800, 140);
        menuFont.draw(game.batch, "Flee", 800, 60);
        game.batch.end();
        stage.draw();
    }

    public void drawCombatAttackMenu(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1,1,1,1);
        shapeRenderer.rect(0,0,960, 180);
        shapeRenderer.end();

        Gdx.gl.glLineWidth(6);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0,0,0,1);
        shapeRenderer.rect(0,0,960, 180);
        shapeRenderer.rect(660,0,300, 180);
        shapeRenderer.end();

        game.batch.begin();
        menuFont.setColor(0,0,0,1);
        calculateCursorPos();
        game.batch.draw(cursorTexture,cursorX, cursorY, 15, 15);
        printAttacks();
        menuFont.draw(game.batch, "Back", 545, 60);
        menuFont.draw(game.batch, "pp", 700, 140);
        menuFont.draw(game.batch, "Type/ Grass", 700, 60);
        menuFont.draw(game.batch, "15/15", 850, 140);
        game.batch.end();
    }

    public void printAttacks(){
        for (int i = 0; i < playerPuckemon.getMoveSet().size(); i++) {
            menuFont.draw(game.batch, playerPuckemon.getMoveSet().get(i).getName(), 70+(i%2)*230, 140-((int)(i/2)*80));
        }



    }

    public Boolean isMainCombatMenu() {
        return mainCombatMenu;
    }

    public void setMainCombatMenu(boolean main){
        mainCombatMenu = main;
    }

    public int getCursorIndex() {
        return cursorIndex;
    }

    public void calculateCursorPos(){
        if(mainCombatMenu){
            cursorX = 580+(cursorIndex%2)*200;
            cursorY = 123-((int)(cursorIndex/2))*80;
        }else{
            if(cursorIndex < playerPuckemon.getMoveSet().size()){
                cursorX = 50+(cursorIndex%2)*230;
                cursorY = 123-((int)(cursorIndex/2))*80;
            }else{
                cursorX = 64+(cursorIndex%3)*230;
                cursorY = 123-((int)(cursorIndex/2))*80;
            }

        }
    }

    public void cursorUP(){
        if(mainCombatMenu){
            if(cursorIndex > 1) cursorIndex -= 2;
        }else{
            if(cursorIndex > 1) cursorIndex -= 2;
        }
    }

    public void cursorDown(){
        if(mainCombatMenu){
            if(cursorIndex < 2) cursorIndex += 2;
        }else{
            if(cursorIndex < playerPuckemon.getMoveSet().size()-2) cursorIndex += 2;
        }
    }

    public void cursorLeft(){
        if(mainCombatMenu){
            if(cursorIndex > 0) cursorIndex -= 1;
        }else{
            if(cursorIndex > 0)cursorIndex -=1;
        }
    }

    public void cursorRight(){
        if(mainCombatMenu){
            if(cursorIndex < 3) cursorIndex += 1;
        }else{
            if(cursorIndex < playerPuckemon.getMoveSet().size()) cursorIndex += 1;
        }
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

    public enum CombatOptions{
        ATTACK,
        INVENTORY,
        SWITCH,
        FLEE,

    }

}
