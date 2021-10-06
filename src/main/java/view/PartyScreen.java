package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;
import model.Model;
import model.entities.Puckemon;
import run.Boot;

import java.util.List;
import java.util.Scanner;

public class PartyScreen implements Screen {

    final Boot game;
    private Model model;
    private int screenWidth, screenHeight;
    private List<Puckemon> party;
    private BitmapFont menuFont;

    OrthographicCamera camera;
    Texture bigRectangle, background, mon1, mon2, mon3, mon4, mon5, mon6, smallRectangle;

    public PartyScreen(final Boot game, Model model) {
        this.game = game;
        this.model = model;
        this.screenWidth = game.getScreenWidth();
        this.screenHeight = game.getScreenHeight();

        menuFont = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"), Gdx.files.internal("fonts/pixelfont.png"), false);
        menuFont.getData().setScale(0.75f);

        this.party = model.getParty();

        camera = new OrthographicCamera();
        camera.setToOrtho(false);

        loadNewTextures();
    }

    private void loadNewTextures(){
        background = new Texture(Gdx.files.internal("PartyBackground.png"));
        bigRectangle = new Texture(Gdx.files.internal("partyAssets/BigRectangle.png"));
        smallRectangle = new Texture(Gdx.files.internal("partyAssets/SmallRectangle.png"));
        mon1 = new Texture(Gdx.files.internal("front/" + getPuckeId(0) + ".png"));
        mon2 = new Texture(Gdx.files.internal("front/" + getPuckeId(1) + ".png"));
        mon3 = new Texture(Gdx.files.internal("front/" + getPuckeId(2) + ".png"));
        mon4 = new Texture(Gdx.files.internal("front/" + getPuckeId(3) + ".png"));
        mon5 = new Texture(Gdx.files.internal("front/" + getPuckeId(4) + ".png"));
        mon6 = new Texture(Gdx.files.internal("front/" + getPuckeId(5) + ".png"));

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
        try {
            return party.get(index).getName();
        }
        catch(Exception e){
            return "MissingNO";
        }
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

    private void drawParty(){
        game.batch.draw(bigRectangle, 32,640-(165+57),328,165);
        game.batch.draw(mon1, 26,640-(116+49),116,116);
        menuFont.draw(game.batch, getPuckeName(0), 151, 640-(44+68));

        for (int i = 0; i < party.size()-1;i++){
            game.batch.draw(smallRectangle, 403,640-(163+109*i),518,93);
            game.batch.draw(getPuckemonTexture(i), 410,640-(158+109*i),85,85);
            menuFont.draw(game.batch, getPuckeName(i+1), 495, 640-(76+141));
        }
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(background, 0, 0, this.camera.viewportWidth, this.camera.viewportHeight);
        drawParty();
        game.batch.end();
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