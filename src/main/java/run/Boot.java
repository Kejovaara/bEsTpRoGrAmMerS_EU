package run;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import controller.IController;
import controller.InputController;
import model.Model;
import view.screens.*;


/**
 * The top level game logic. Is a View-Controller handler that handles view and controller switching.
 * @author Emil Jonsson
 * @author Rasmus Almryd
 */
public class Boot extends Game implements VCHandler{

    private InputController controller;

    public SpriteBatch batch;
    private BitmapFont font;

    int screenWidth, screenHeight;

    private Screen mainScreen, combatScreen, invetoryScreen, partyScreen, gameOverScreen, victoryScreen;

    /**
     * Constructor.
     * @param screenHeight the height of the screen.
     * @param screenWidth teh width of the screen.
     */
    public Boot(int screenHeight, int screenWidth){
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    /**
     * Creates the screens and sets the view to the main starting menu.
     */
    @Override
    public void create() {
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, screenWidth, screenHeight);
        batch = new SpriteBatch();
        font = new BitmapFont();

        Model model = new Model();

        this.mainScreen = new MainMenuScreen(batch);
        this.combatScreen = new CombatScreen(this, model);
        this.invetoryScreen = new InventoryScreen(this, model);
        this.partyScreen = new PartyScreen(this, model);
        this.gameOverScreen = new GameOverScreen(batch);
        this.victoryScreen = new VictoryScreen(batch);

        setView(Screens.MAIN_MENU);

        controller = new InputController(this, model);
        setController(InputController.Controllers.MAIN_MENU);

    }

    /**
     * Renders the game and updates the controller.
     */
    public void render() {
        IController oldController = controller.getActiveController();
        controller.update();
        //Avoid double input
        if(oldController == controller.getActiveController()) super.render();
    }

    /**
     * Disposes the batch and font.
     */
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    /**
     * Switches the view to the screen specified.
     * @param screen which screen that should be switched to.
     */
    @Override
    public void setView(Screens screen) {
        switch (screen){
            case MAIN_MENU:
                switchView(mainScreen);
                break;
            case COMBAT:
                switchView(combatScreen);
                break;
            case PARTY:
                switchView(partyScreen);
                break;
            case INVENTORY:
                switchView(invetoryScreen);
                break;
            case GAME_OVER:
                switchView(gameOverScreen);
                break;
            case VICTORY:
                switchView(victoryScreen);
                break;
        }
    }

    private void switchView(Screen view){
        this.setScreen(view);
    }

    /**
     * Switches to the specified controller.
     * @param controllerEnum the controller to be switched to.
     */
    @Override
    public void setController(InputController.Controllers controllerEnum){
        controller.switchController(controllerEnum);
    }

}
