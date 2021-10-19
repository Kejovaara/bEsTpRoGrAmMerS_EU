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
import input.InputController;
import model.Model;
import model.entities.OwnedPuckemon;
import model.entities.Puckemon;
import run.Boot;

import java.util.List;
import java.util.Scanner;

public class PartyScreen implements Screen {

    final Boot game;
    private Model model;
    private List<OwnedPuckemon> party;
    private BitmapFont partyFont;
    private ShapeRenderer shapeRenderer;
    private int targetIndex = 0;
    private int lastTarget = 1;
    private int posX = 433;
    private int posY = 510;
    private Label partyLabel;
    private Stage stage;
    private String message = "Pick a Puckemon";

    OrthographicCamera camera;
    Texture background, mon1, mon2, mon3, mon4, mon5, mon6;

    public PartyScreen(final Boot game, Model model) {
        this.game = game;
        this.model = model;

        shapeRenderer = new ShapeRenderer();

        partyFont = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"));
        partyFont.getData().setScale(0.75f);
        partyFont.setColor(0,0,0,1);



        Label.LabelStyle fontStyle = new Label.LabelStyle();
        fontStyle.font = partyFont;
        fontStyle.fontColor = Color.BLACK;

        stage = new Stage();
        partyLabel = new Label(message,fontStyle);
        partyLabel.setSize(300,300);
        partyLabel.setPosition(55,60);
        partyLabel.setWrap(true);
        stage.addActor(partyLabel);

        this.party = model.getParty();

        camera = new OrthographicCamera();
        camera.setToOrtho(false);

        loadNewTextures();
    }

    private void loadNewTextures(){
        background = new Texture(Gdx.files.internal("PartyBackground.png"));
        mon1 = new Texture(Gdx.files.internal("front/" + getPuckeId(0) + ".png"));
        mon2 = new Texture(Gdx.files.internal("front/" + getPuckeId(1) + ".png"));
        mon3 = new Texture(Gdx.files.internal("front/" + getPuckeId(2) + ".png"));
        mon4 = new Texture(Gdx.files.internal("front/" + getPuckeId(3) + ".png"));
        mon5 = new Texture(Gdx.files.internal("front/" + getPuckeId(4) + ".png"));
        mon6 = new Texture(Gdx.files.internal("front/" + getPuckeId(5) + ".png"));
    }

    public void switchPuckemon(){
        if(targetIndex != 0 && party.get(targetIndex).getHealth()!=0){
            model.switchPuckemon(targetIndex);
            if (party.get(targetIndex).getHealth()!=0){
                model.useSwitch();
            }
            game.setView(Screens.COMBAT);
            game.setController(InputController.Controllers.COMBAT);
        }else{
            setMessage(party.get(targetIndex).getName() + " can't battle anymore");
        }
    }

    public boolean backButton(){
        return targetIndex == 6;
    }

    public boolean requiredSwitch(){
        return party.get(0).getHealth() != 0;
    }

    private int getPuckeId(int index){
        try {
            return party.get(index).getId();
        }
        catch(Exception e){
            return 0;
        }
    }

    private String getPuckeName(int index){
        return party.get(index).getName();
    }

    private int getPuckeCurrentHP(int index){
        return party.get(index).getHealth();
    }

    private int getPuckeMaxHP(int index){
        return party.get(index).getMaxHealth();
    }

    private int getPuckeLevel(int index){
        return party.get(index).getLevel();
    }

    private Texture getPuckemonTexture(int index){
        switch (index) {
            case 0:
                return mon2;
            case 1:
                return mon3;
            case 2:
                return mon4;
            case 3:
                return mon5;
            case 4:
                return mon6;
            default:throw new IllegalArgumentException("Something strange happend");
        }
    }

    public void moveTargetIndexUpDown(boolean up){

        if(up && (targetIndex==0 )) {
            targetIndex = 6;
        }
        else if(up && targetIndex!=6){
            targetIndex--;
        }
        else if(up){
            targetIndex = party.size()-1;
        }

        if(!up && targetIndex==6){
            targetIndex=0;
        }
        else if(!up && targetIndex==party.size()-1){
            targetIndex = 6;
        }
        else if(!up){
            targetIndex++;
        }
    }

    public void moveTargetIndexRightLeft(boolean right){
        if(targetIndex!=0){
           lastTarget = targetIndex;
        }
        if(right && targetIndex > 0){
            targetIndex = 0;
        }
        else if(right && targetIndex == 0){
            targetIndex = lastTarget;
        }
        if(!right && targetIndex > 0){
            targetIndex = 0;
        } else if(!right){
            targetIndex = lastTarget;
        }
    }

    private void colorPicker(int index){
        shapeRenderer.setColor(103/255f, 172/255f, 194/255f,1);

        if(index==6){
            shapeRenderer.setColor(200/255f, 200/255f, 200/255f,1);
        }
        if(index == 6 && index == targetIndex){
            shapeRenderer.setColor(255/255f, 255/255f, 255/255f,1);
        }

        if (index!=6){
            if (targetIndex == index){
                shapeRenderer.setColor(138/255f, 206/255f, 227/255f,1);
            }
            if(party.get(index).getHealth()==0){
                shapeRenderer.setColor(194/255f, 70/255f, 70/255f,1);
            }
            if(party.get(index).getHealth()==0 && targetIndex == index){
                shapeRenderer.setColor(205/255f, 97/255f, 97/255f,1);
            }
        }
    }

    public void setMessage(String message){
        partyLabel.setText(message);
    }

    private void draw(){
        shapeRenderer.setColor(57/255f, 57/255f, 57/255f,1);
        shapeRenderer.rect( 32,640-(165+57),328,165);
        colorPicker(0);
        shapeRenderer.rect(36,640-(161+57),320,157);

        shapeRenderer.setColor(57/255f, 57/255f, 57/255f,1);
        shapeRenderer.rect(62, 640-(148+26), 255,26);
        shapeRenderer.setColor(0.698f, 1, 0.729f,1);
        shapeRenderer.rect(64, 640-(150+22), ((float) model.getPlayerPuckemon().getHealth()/model.getPlayerPuckemon().getMaxHealth())*251,22);

        shapeRenderer.setColor(57/255f, 57/255f, 57/255f,1);
        shapeRenderer.rect( 780,30,100,40);
        colorPicker(6);
        shapeRenderer.rect(782,32,96,36);

        for (int i = 0; i < party.size()-1;i++) {
            shapeRenderer.setColor(57/255f, 57/255f, 57/255f,1);
            shapeRenderer.rect(posX, posY-102*i, 488,93);
            colorPicker(i+1);
            shapeRenderer.rect(posX+3, posY+3-102*i, 482,87);

            shapeRenderer.setColor(57/255f, 57/255f, 57/255f,1);
            shapeRenderer.rect(posX+190, posY+30-102*i, 265,21);
            shapeRenderer.setColor(0.698f, 1, 0.729f,1);
            shapeRenderer.rect(posX+192, posY+32-102*i, ((float) party.get(i+1).getHealth()/party.get(i+1).getMaxHealth())*261,17);
        }
    }

    private void renderParty(){
        game.batch.draw(mon1, 26,640-(116+49),116,116);
        partyFont.draw(game.batch, ("LV. "+getPuckeLevel(0)),135,640-(110));
        partyFont.draw(game.batch, getPuckeName(0), 135, 640-(80));
        partyFont.draw(game.batch, (getPuckeCurrentHP(0)+"/"+getPuckeMaxHP(0)), 265, 640-(180));

        partyFont.draw(game.batch, ("BACK"),798,58);

        for (int i = 0; i < party.size()-1;i++){
            game.batch.draw(getPuckemonTexture(i), posX+8,posY+5-102*i,85,85);/**/

            partyFont.draw(game.batch, getPuckeName(i+1), posX+100, posY+80-102*i);
            partyFont.draw(game.batch, (getPuckeCurrentHP(i+1)+"/"+getPuckeMaxHP(i+1)), posX+350, posY+25-102*i);
            partyFont.draw(game.batch, ("LV. "+getPuckeLevel(i+1)),posX+105,posY+50-102*i);
        }

    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(background, 0, 0, this.camera.viewportWidth, this.camera.viewportHeight);
        game.batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        draw();
        shapeRenderer.end();

        game.batch.begin();
        renderParty();
        game.batch.end();
        stage.draw();

    }

    @Override
    public void show() {
        loadNewTextures();
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