package input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import run.VCHandler;
import model.Model;
import view.Screens;

/**
 * A class to handle input events when the MainMenuScreen is active.
 * @author Rasmus Almryd
 */
public class MenuController implements IController{
    private Model model;
    private final VCHandler handler;

    /**
     * Constructor of MenuController
     * @param handler used to switch controller and/or screen.
     * @param model used to check and interact with the model.
     */
    public MenuController(VCHandler handler, Model model) {
        this.handler = handler;
        this.model = model;
    }

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