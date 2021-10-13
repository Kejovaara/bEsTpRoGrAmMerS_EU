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
import model.attack.Attack;
import model.entities.Puckemon;
import run.Boot;
import view.animation.*;

import java.util.ArrayList;
import java.util.List;

public class CombatScreen implements Screen, EffectObserver{

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

    private Label label;

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

        //COMABT BOX TEXT
        stage = new Stage();
        combatFont = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"));;
        combatFont.getData().setScale(0.75f);

        Label.LabelStyle fontStyle = new Label.LabelStyle();
        fontStyle.font = combatFont;
        fontStyle.fontColor = Color.BLACK;

        label = new Label("What will " + model.getPlayerPuckemon().getName() + " do?",fontStyle);
        label.setSize(520,10);
        label.setPosition(30,90);
        label.setWrap(true);
        stage.addActor(label);

        playerPuck = getTexture(model.getPlayerPuckemon().getId(), false);
        trainerPuck = getTexture(model.getTrainerPuckemon().getId(), true);
        //pucke2 = new Texture(Gdx.files.internal("PuckemonBack/1.png"));
        background = new Texture(Gdx.files.internal("Background.png"));
        cursorTexture = new Texture(Gdx.files.internal("Arrow.png"));

        //ANIMATION
        EffectAnimationsHandler.getInstance().addObserver(this);
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

        if(model.getPlayerPuckemon().getHealth() <= 0){
            label.setText("Your Puckemon fainted, Press any key to switch");
            drawMainCombatMenu();
        }
        else if (mainCombatMenu){
            label.setText("What will " + model.getPlayerPuckemon().getName() + " do?");
            drawMainCombatMenu();
            drawCursor();
        }
        else {
            drawCombatAttackMenu();
            drawCursor();
        }

        drawAnimations();


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

    private void drawCursor(){
        game.batch.begin();
        calculateCursorPos();
        game.batch.draw(cursorTexture,cursorX, cursorY, 15, 15);
        game.batch.end();
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

        printAttacks();
        menuFont.setColor(0,0,0,1);
        menuFont.draw(game.batch, "Back", 545, 60);
        if(cursorIndex < model.getPlayerPuckemon().getMoveSet().size()){
            Attack attack = model.getPlayerPuckemon().getMoveSet().get(cursorIndex);
            menuFont.draw(game.batch, "pp", 700, 140);
            menuFont.draw(game.batch, "Type/ " +attack.getType(), 700, 60);
            menuFont.draw(game.batch, attack.getPP()+"/"+attack.getBasePP(), 850, 140);
        }

        game.batch.end();
    }

    public void printAttacks(){
        for (int i = 0; i < model.getPlayerPuckemon().getMoveSet().size(); i++) {
            if (model.getAttack(i).getPP()>0) menuFont.setColor(0,0,0,1);
            else menuFont.setColor(0.6f,0.6f,0.6f,1);
            menuFont.draw(game.batch, model.getAttack(i).getName(), 70+(i%2)*230, 140-((int)(i/2)*80));
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
            if(cursorIndex < model.getPlayerPuckemon().getMoveSet().size()){
                cursorX = 50+(cursorIndex%2)*230;
                cursorY = 123-((int)(cursorIndex/2))*80;
            }else{
                cursorX = 524;
                cursorY = 43;
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
            if(cursorIndex < model.getPlayerPuckemon().getMoveSet().size()-2) cursorIndex += 2;
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
            if(cursorIndex < model.getPlayerPuckemon().getMoveSet().size()) cursorIndex += 1;
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



    public enum CombatOptions{
        ATTACK,
        INVENTORY,
        SWITCH,
        FLEE,

    }

}
