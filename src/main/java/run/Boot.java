package run;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import input.InputController;
import model.Model;
import view.*;

public class Boot extends Game implements VCHandler{

    private Model model;
    private Screen activeScreen;
    private InputController controller;

    private OrthographicCamera camera;
    public SpriteBatch batch;
    private BitmapFont font;

    int screenWidth, screenHeight;
    public int frame = 0;

    private Screen mainScreen, combatScreen, invetoryScreen, partyScreen;

    public Boot(int screenHeight, int screenWidth){
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, screenWidth, screenHeight);
        batch = new SpriteBatch();
        font = new BitmapFont();

        model = new Model();

        activeScreen = new MainMenuScreen(this, model);
        this.setScreen(activeScreen);

        controller = new InputController(this, model);
        controller.switchController(InputController.Controllers.MAIN_MENU);

        this.mainScreen = new MainMenuScreen(this, model);
        this.combatScreen = new CombatScreen(this, model);
        this.invetoryScreen = new InventoryScreen(this, model);
        this.partyScreen = new PartyScreen(this, model);
    }

    public void render() {
        frame++;
        controller.update();
        super.render();
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public void setView(Screens screen) {
        switch (screen){
            case MAIN_MENU:
                switchView(mainScreen);
                frame = 0;
                break;
            case COMBAT:
                System.out.println("tryckt: "+ Gdx.input.isKeyJustPressed(Input.Keys.ENTER));
                switchView(combatScreen);
                frame = 0;
                break;
            case PARTY:
                switchView(partyScreen);
                frame = 0;
                break;
            case INVENTORY:
                switchView(invetoryScreen);
                frame = 0;
                break;
        }
    }

    private void switchView(Screen view){
        activeScreen = view;
        this.setScreen(view);
    }

    @Override
    public void setController(InputController.Controllers controllerEnum){
        controller.switchController(controllerEnum);
    }

    public void setView(Screen view){
        activeScreen = view;
        this.setScreen(view);

    }

    public Screen getActiveScreen() {
        return activeScreen;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    //TODO: learn more about screens https://libgdx.com/dev/simple-game-extended/
    // Maybe there can be a screen for menu, settings, map, and combat
}
