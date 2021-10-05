package run;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import input.InputController;
import model.Model;
import view.MainMenuScreen;

public class Boot extends Game {

    private Model model = new Model();

    private OrthographicCamera camera;
    public SpriteBatch batch;
    public BitmapFont font;

    public InputController controller;

    int screenWidth, screenHeight;

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
        this.setScreen(new MainMenuScreen(this, model));

        controller = new InputController(this, model);
        controller.switchController(InputController.Controllers.MAIN_MENU);
    }

    public void render() {
        controller.update();
        super.render();
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
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
