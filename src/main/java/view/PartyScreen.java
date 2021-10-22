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
import model.entities.puckemon.OwnedPuckemon;
import run.Boot;
import view.menu.Menu;
import view.menu.MenuBuilder;

import java.util.List;

public class PartyScreen implements Screen, IView {

    final Boot game;
    private Model model;
    private List<OwnedPuckemon> party;
    private BitmapFont partyFont;
    private ShapeRenderer shapeRenderer;
    private Label partyLabel;
    private Stage stage;
    private String message = "Pick a Puckemon";

    OrthographicCamera camera;
    Texture background;

    private Menu menu;

    public PartyScreen(final Boot game, Model model) {
        this.game = game;
        this.model = model;

        shapeRenderer = new ShapeRenderer();

        background = new Texture(Gdx.files.internal("PartyBackground.png"));
        partyFont = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"));
        partyFont.getData().setScale(0.75f);
        partyFont.setColor(0,0,0,1);

        menu = MenuBuilder.getPartyMenu(game.batch, game,this, model);

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
    }

    public void setMessage(String message){
        partyLabel.setText(message);
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(background, 0, 0, this.camera.viewportWidth, this.camera.viewportHeight);
        game.batch.end();

        menu.render();

    }

    @Override
    public void show() {
        menu = MenuBuilder.getPartyMenu(game.batch,game, this, model);
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
    public void switchMenu(int index) {

    }
}