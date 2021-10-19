package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import view.animation.*;
import view.menu.Menu;
import view.menu.MenuFactory;
import view.message.MessageHandler;
import view.screenObjects.RectangleBorder;

import java.util.ArrayList;
import java.util.List;

public class CombatScreen implements Screen, EffectObserver, MessageObserver, IView{

    final Boot game;
    private Model model;
    private int screenWidth, screenHeight;
    private ShapeRenderer shapeRenderer;
    private Stage stage;

    private BitmapFont menuFont;
    private BitmapFont combatFont;
    private BitmapFont statsFont;

    OrthographicCamera camera;
    Texture playerPuck, enemyPuck, background, cursorTexture;

    private Boolean mainCombatMenu = true;
    private int cursorIndex = 0;
    private int cursorX,cursorY;

    private Label topLabel;
    private Label label;

    private Menu mainMenu;
    private Menu attackMenu;
    private Menu activeMenu;

    private RectangleBorder mainMenuBackground1, mainMenuBackground2;

    private List<Animable> playerAnimations = new ArrayList<>();
    private List<Animable> enemyAnimations = new ArrayList<>();

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

        camera = new OrthographicCamera();
        camera.setToOrtho(false);

        mainMenu = MenuFactory.getMainCombatMenu(game,this, model);
        attackMenu = MenuFactory.getAttackCombatMenu( game,this, model);
        activeMenu = mainMenu;

        mainMenuBackground1 = new RectangleBorder(0,0,960,180,Color.BLACK,Color.WHITE,8);
        mainMenuBackground2 = new RectangleBorder(560,0,400,180,Color.BLACK,Color.WHITE,8);


        //COMABT BOX TEXT
        stage = new Stage();
        combatFont = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"));;
        combatFont.getData().setScale(0.75f);

        Label.LabelStyle fontStyle = new Label.LabelStyle();
        fontStyle.font = combatFont;
        fontStyle.fontColor = Color.BLACK;

        label = new Label("What will " + model.getPlayerPuckemon().getName() + " do?",fontStyle);
        label.setSize(520,10);
        label.setPosition(30,60);
        label.setWrap(true);
        stage.addActor(label);

        topLabel = new Label("",fontStyle);
        topLabel.setSize(520,10);
        topLabel.setPosition(30,110);
        topLabel.setWrap(true);
        stage.addActor(topLabel);

        background = new Texture(Gdx.files.internal("Background.png"));
        cursorTexture = new Texture(Gdx.files.internal("Arrow.png"));

        //ANIMATION
        EffectAnimationsHandler.getInstance().addObserver(this);
        //MESSAGE
        MessageHandler.getInstance().addObserver(this);
        Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY);
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
        game.batch.draw(enemyPuck, 570, 400, 192, 192);
        game.batch.draw(playerPuck, 200, 110, 256, 256);
        game.batch.end();

        drawPuckeStats();


        mainMenuBackground1.render();
        stage.draw();
        if (model.getPlayerPuckemon().getHealth() <= 0){
            label.setText("Your Puckemon fainted, Press any key to switch");
        }else{
            drawAnimations();
            mainMenuBackground2.render();
            activeMenu.render();
        }
    }

    private void drawAnimations() {
        for(int i = 0; i < playerAnimations.size(); i++){
            playerAnimations.get(i).render(game.batch);
            if (playerAnimations.get(i).isDone()) playerAnimations.remove(i);
        }
        for(int i = 0; i < enemyAnimations.size(); i++){
            enemyAnimations.get(i).render(game.batch);
            if (enemyAnimations.get(i).isDone()) enemyAnimations.remove(i);
        }
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
        shapeRenderer.rect(60, this.camera.viewportHeight-100,((float)model.getTrainerPuckemon().getHealth()/model.getTrainerPuckemon().getMaxHealth())*360,40);

        shapeRenderer.setColor(0.7f,0.7f,0.7f,1);
        shapeRenderer.rect(this.camera.viewportWidth-420, 240, 360,40);
        shapeRenderer.setColor(0.698f, 1, 0.729f,1);
        shapeRenderer.rect(this.camera.viewportWidth-420, 240, ((float)model.getPlayerPuckemon().getHealth()/model.getPlayerPuckemon().getMaxHealth())*360,40);

        shapeRenderer.end();

        Gdx.gl.glLineWidth(4);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0,0,0,1);
        shapeRenderer.rect(40,this.camera.viewportHeight-140, 400, 120);
        shapeRenderer.rect(this.camera.viewportWidth-440,200, 400, 120);
        shapeRenderer.end();

        game.batch.begin();
        statsFont.setColor(0,0,0,1);
        statsFont.draw(game.batch, model.getTrainerPuckemon().getName(),60,this.camera.viewportHeight-40);
        statsFont.draw(game.batch, "Lv "+model.getTrainerPuckemon().getLevel(),360,this.camera.viewportHeight-40);

        statsFont.draw(game.batch, model.getPlayerPuckemon().getName(),this.camera.viewportWidth-420,300);
        statsFont.draw(game.batch, "Lv "+model.getPlayerPuckemon().getLevel(),this.camera.viewportWidth-120,300);
        statsFont.draw(game.batch, model.getPlayerPuckemon().getHealth() + " / " + model.getPlayerPuckemon().getMaxHealth(),this.camera.viewportWidth-120,230);


        game.batch.end();
    }

    @Override
    public void show() {
        attackMenu = MenuFactory.getAttackCombatMenu(game,this, model);
        activeMenu = mainMenu;

        playerPuck = getTexture(model.getPlayerPuckemon().getId(),false);
        enemyPuck = getTexture(model.getTrainerPuckemon().getId(), true);
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
    public void SetMessage(String message) {
        this.topLabel.setText(message);
    }

    @Override
    public void onDamage(int damage, Puckemon damageReceiver) {
        if(damageReceiver == model.getPlayerPuckemon()) {
            playerAnimations.add(new EffectAnimation(damage, 210+(playerAnimations.size()*80), 330,"DMG", new Color(0.7f,0,0,1)));
        }
        else {
            enemyAnimations.add(new EffectAnimation(damage, 530 +(enemyAnimations.size()*80), (int)camera.viewportHeight-40,"DMG", new Color(0.7f,0,0,1)));
        }
    }

    @Override
    public void onHeal(int heal, Puckemon healReceiver) {
        if(healReceiver == model.getPlayerPuckemon()) {
            playerAnimations.add(new EffectAnimation(heal, 210+(playerAnimations.size()*80), 330,"HP+", new Color(0,0.7f,0,1)));
        }
        else {
            enemyAnimations.add(new EffectAnimation(heal,530 +(enemyAnimations.size()*80), (int)camera.viewportHeight-40,"HP+", new Color(0,0.7f,0,1)));
        }
    }

    @Override
    public void onAttackBuff(int buff, Puckemon buffReceiver) {
        if(buffReceiver == model.getPlayerPuckemon()){
            playerAnimations.add(new EffectAnimation(buff,210+(playerAnimations.size()*80), 330,"ATK"));
        }
        else {
            enemyAnimations.add(new EffectAnimation(buff,530 +(enemyAnimations.size()*80), (int)camera.viewportHeight-40,"ATK"));
        }
    }

    @Override
    public void switchMenu(int index) {
        if(index == 0) activeMenu = mainMenu;
        else activeMenu = attackMenu;
    }



}
