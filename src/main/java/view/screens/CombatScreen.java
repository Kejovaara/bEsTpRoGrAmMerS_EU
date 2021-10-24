package view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.ScreenUtils;
import model.Model;
import model.puckemon.IPuckemon;
import model.puckemon.Puckemon;
import run.VCHandler;
import serviceControllers.observers.EffectHandler;
import serviceControllers.observers.EffectObserver;
import serviceControllers.observers.MessageObserver;
import view.IView;
import view.animation.*;
import view.menu.Menu;
import view.menu.MenuBuilder;
import serviceControllers.observers.MessageHandler;
import view.screenObjects.HealthBar;
import view.screenObjects.RectangleBorder;

import java.util.ArrayList;
import java.util.List;

/**
 * The screen for the main combat of the game.
 * @author Rasmus Almryd
 * @author André Kejovaara
 * @author Emil Jonsson
 */
public class CombatScreen implements Screen, EffectObserver, MessageObserver, IView {

    private final SpriteBatch batch;
    private final VCHandler handler;
    private final Model model;
    private final ShapeRenderer shapeRenderer;
    private final Stage stage;

    private final BitmapFont statsFont;

    private final TextAnimation textAnimator;

    private final OrthographicCamera camera;
    Texture playerPuck, enemyPuck, background, cursorTexture;

    private final Menu mainMenu;
    private Menu attackMenu;
    private Menu activeMenu;
    private final HealthBar enemyBar, playerBar;
    private final RectangleBorder enemyBox, playerBox;

    private final RectangleBorder mainMenuBackground1, mainMenuBackground2;
    private IPuckemon activeEnemyPuckemon;

    private final List<Animable> playerAnimations = new ArrayList<>();
    private final List<Animable> enemyAnimations = new ArrayList<>();

    private boolean isPrinted = false;

    /**
     * Constructor for CombatScreen.
     * @param handler used by the controllers to switch controller and/or screen.
     * @param batch This is used to display IRender objects
     * @param model used to get parts of the model to display.
     */
    public CombatScreen(VCHandler handler,SpriteBatch batch, Model model) {
        this.handler = handler;
        this.batch = batch;
        this.model = model;

        shapeRenderer = new ShapeRenderer();

        //Menu Font
        BitmapFont menuFont = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"), Gdx.files.internal("fonts/pixelfont.png"), false);
        menuFont.getData().setScale(0.75f);



        statsFont = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"), Gdx.files.internal("fonts/pixelfont.png"), false);
        statsFont.getData().setScale(0.5f);

        camera = new OrthographicCamera();
        camera.setToOrtho(false);

        enemyBox = new RectangleBorder(40, (int)this.camera.viewportHeight-140, 400,120, Color.BLACK,Color.WHITE, 4);
        playerBox = new RectangleBorder((int)this.camera.viewportWidth-440, 200,400,120, Color.BLACK,Color.WHITE,4);
        enemyBar = new HealthBar(60,(int) this.camera.viewportHeight-100, 360,40,model.getOpponentPuckemon().getMaxHealth(),model.getOpponentPuckemon().getHealth());
        playerBar = new HealthBar((int)this.camera.viewportWidth-420,240, 360,40,model.getActivePlayerPuckemon().getHealth(),model.getActivePlayerPuckemon().getMaxHealth());

        mainMenu = MenuBuilder.getMainCombatMenu(batch, handler,this, model);
        attackMenu = MenuBuilder.getAttackCombatMenu( batch,this, model);
        activeMenu = mainMenu;

        mainMenuBackground1 = new RectangleBorder(0,0,960,180,Color.BLACK,Color.WHITE,8);
        mainMenuBackground2 = new RectangleBorder(560,0,400,180,Color.BLACK,Color.WHITE,8);

        activeEnemyPuckemon = model.getOpponentPuckemon();

        //COMABT BOX TEXT
        stage = new Stage();
        BitmapFont combatFont = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"));
        combatFont.getData().setScale(0.75f);

        Label.LabelStyle fontStyle = new Label.LabelStyle();
        fontStyle.font = combatFont;
        fontStyle.fontColor = Color.BLACK;

        Label topLabel = new Label("",fontStyle);
        topLabel.setSize(520,10);
        topLabel.setPosition(30,80);
        topLabel.setWrap(true);
        //stage.addActor(topLabel);

        textAnimator = new TextAnimation(topLabel, "What will " + model.getActivePlayerPuckemon().getName() + " do?");

        background = new Texture(Gdx.files.internal("Background.png"));
        cursorTexture = new Texture(Gdx.files.internal("Arrow.png"));

        //ANIMATION
        EffectHandler.getInstance().addObserver(this);
        //MESSAGE
        MessageHandler.getInstance().addObserver(this);
    }

    /**
     * Gets the texture of the specified Puckemon id.
     * @param id id of the Puckemon.
     * @param front if the texture should be the front och back version of the Puckemon.
     * @return Texture of the specified Puckemon in the form (back or front) specified.
     */
    private Texture getTexture(int id, boolean front) {
        if(front) {
            return new Texture(Gdx.files.internal("front/" + id + ".png"));
        }else{
            return new Texture(Gdx.files.internal("back/" + id + ".png"));
        }
    }


    /**
     * Renders the CombatScreen.
     * @param delta libGDX variable to prevent hardware acceleration.
     */
    @Override
    public void render(float delta) {
        checkOpponentTexture();

        ScreenUtils.clear(	0.906f, 0.965f, 0.984f,1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.8f,0.8f,0.8f,1);
        shapeRenderer.ellipse(520,400, 280, 60);

        shapeRenderer.end();
        enemyBox.render();
        playerBox.render();
        enemyBar.render();
        playerBar.render();


        batch.begin();
        batch.draw(enemyPuck, 570, 400, 192, 192);
        batch.draw(playerPuck, 200, 110, 256, 256);
        batch.end();

        drawPuckeStats();

        mainMenuBackground1.render();
        stage.draw();

        if (model.getActivePlayerPuckemon().getHealth() <= 0){
            if (!isPrinted){
                faintedPuckemonText();
                isPrinted = true;
            }
            drawTextAnimations();
            drawAnimations();
        } else if (activeEnemyPuckemon.getHealth() <= 0){
            if (!isPrinted){
                faintedOpponentText();
                isPrinted = true;
            }
            drawTextAnimations();
            drawAnimations();
            mainMenuBackground2.render();
            activeMenu.render();
        } else{
            drawTextAnimations();
            drawAnimations();
            mainMenuBackground2.render();
            activeMenu.render();
            isPrinted = false;
        }
    }

    private void checkOpponentTexture(){
        if(activeEnemyPuckemon != model.getOpponentPuckemon()){
            enemyPuck = getTexture(model.getOpponentPuckemon().getId(), true);
            activeEnemyPuckemon = model.getOpponentPuckemon();
        }
    }

    private void drawAnimations() {
        for(int i = 0; i < playerAnimations.size(); i++){
            playerAnimations.get(i).render(batch);
            if (playerAnimations.get(i).isDone()) playerAnimations.remove(i);
        }
        for(int i = 0; i < enemyAnimations.size(); i++){
            enemyAnimations.get(i).render(batch);
            if (enemyAnimations.get(i).isDone()) enemyAnimations.remove(i);
        }
    }

    private void drawTextAnimations(){
        textAnimator.render(batch);
    }

    private void faintedPuckemonText(){
        String message = " Your Puckemon fainted, Press any key to switch";
        textAnimator.addMessage(message);
    }

    private void faintedOpponentText(){
        String message = "Opponent Puckemon fainted!";
        textAnimator.setMessage(message);
    }

    private void promptText(){
        String message = "What will " + model.getActivePlayerPuckemon().getName() + " do?";
        textAnimator.setMessage(message);
    }

    private void drawPuckeStats(){
        enemyBar.setHealth(model.getOpponentPuckemon().getHealth());
        enemyBar.setMaxHealth(model.getOpponentPuckemon().getMaxHealth());
        playerBar.setHealth(model.getActivePlayerPuckemon().getHealth());
        playerBar.setMaxHealth(model.getActivePlayerPuckemon().getMaxHealth());


        batch.begin();
        statsFont.setColor(0,0,0,1);
        statsFont.draw(batch, model.getOpponentPuckemon().getName(),60,this.camera.viewportHeight-40);
        statsFont.draw(batch, "Lv "+model.getOpponentPuckemon().getLevel(),360,this.camera.viewportHeight-40);

        statsFont.draw(batch, model.getActivePlayerPuckemon().getName(),this.camera.viewportWidth-420,300);
        statsFont.draw(batch, "Lv "+model.getActivePlayerPuckemon().getLevel(),this.camera.viewportWidth-120,300);
        statsFont.draw(batch, model.getActivePlayerPuckemon().getHealth() + " / " + model.getActivePlayerPuckemon().getMaxHealth(),this.camera.viewportWidth-120,230);


        batch.end();
    }

    /**
     * Updates the menu and Puckemons each time this view is displayed.
     */
    @Override
    public void show() {
        attackMenu = MenuBuilder.getAttackCombatMenu(batch,this, model);
        activeMenu = mainMenu;

        playerPuck = getTexture(model.getActivePlayerPuckemon().getId(),false);
        enemyPuck = getTexture(model.getOpponentPuckemon().getId(), true);

        //if (textAnimator.isDone())textAnimator.setMessage("What will " + model.getActivePlayerPuckemon().getName() + " do?");
        if(textAnimator.isDone())promptText();
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
        textAnimator.setMessage("");
        playerAnimations.clear();
        enemyAnimations.clear();
    }

    @Override
    public void dispose() {

    }

    /**
     * Generates EffectAnimation that symbolize damage.
     * @param damage damage dealt by effect.
     * @param damageReceiver the recipient of the damage.
     */
    @Override
    public void onDamage(int damage, Puckemon damageReceiver) {
        if(damageReceiver == model.getActivePlayerPuckemon()) {
            playerAnimations.add(new EffectAnimation(damage, 210+(playerAnimations.size()*80), 330,"DMG", new Color(0.7f,0,0,1)));
        }
        else {
            enemyAnimations.add(new EffectAnimation(damage, 530 +(enemyAnimations.size()*80), (int)camera.viewportHeight-60,"DMG", new Color(0.7f,0,0,1)));
        }
    }

    /**
     * Generates EffectAnimation that symbolize healing.
     * @param heal restores int amount of Hp to healReceiver.
     * @param healReceiver The puckemon receiving the healing.
     */
    @Override
    public void onHeal(int heal, Puckemon healReceiver) {
        if(healReceiver == model.getActivePlayerPuckemon()) {
            playerAnimations.add(new EffectAnimation(heal, 210+(playerAnimations.size()*80), 330,"HP+", new Color(0,0.7f,0,1)));
        }
        else {
            enemyAnimations.add(new EffectAnimation(heal,530 +(enemyAnimations.size()*80), (int)camera.viewportHeight-60,"HP+", new Color(0,0.7f,0,1)));
        }
    }

    /**
     * Generates a buff animation that symbolize how much attack buff your puckemon gets.
     * @param buff Buffs (increases stat) of the buff receiver.
     * @param buffReceiver The one receiver.
     */
    @Override
    public void onAttackBuff(int buff, Puckemon buffReceiver) {
        if(buffReceiver == model.getActivePlayerPuckemon()){
            playerAnimations.add(new EffectAnimation(buff,210+(playerAnimations.size()*80), 330,"ATK"));
        }
        else {
            enemyAnimations.add(new EffectAnimation(buff,530 +(enemyAnimations.size()*80), (int)camera.viewportHeight-60,"ATK"));
        }
    }

    /**
     * Switches the menu between attack and main menu.
     * @param index index of menu.
     */
    @Override
    public void switchMenu(int index) {
        if(index == 0) activeMenu = mainMenu;
        else activeMenu = attackMenu;
    }

    /**
     * Method implemented by observing messages as a MessageObserver.
     * @param message a mmessage notified from notifier.
     */
    @Override
    public void SetMessage(String message) {
        textAnimator.setMessage(message);


    }
}
