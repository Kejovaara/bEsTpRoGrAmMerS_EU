package input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import run.VCHandler;
import view.Screens;

/**
 * A class to handle input events when the MainMenuScreen is active.
 * @author Rasmus Almryd
 */
public class MenuController implements IController{
    private final VCHandler handler;

    /**
     * Constructor of MenuController
     * @param handler used to switch controller and/or screen.
     */
    public MenuController(VCHandler handler) {
        this.handler = handler;
    }

    /**
     * If escape is pressed, exit app. If enter is pressed, switch to combat screen.
     */
    @Override
    public void update() {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            handler.setController(InputController.Controllers.COMBAT);
            handler.setView(Screens.COMBAT);
        }
    }
}