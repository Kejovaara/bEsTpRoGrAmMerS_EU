package input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import model.Model;
import run.Boot;
import run.VCHandler;
import view.CombatScreen;
import view.PartyScreen;
import view.Screens;

/**
 * A class to handle input events when the PartyScreen is active.
 * @author Rasmus Almryd
 */
public class PartyController implements IController{

    private Model model;
    private final VCHandler handler;

    /**
     * Constructor of PartyController
     * @param handler used to switch controller and/or screen.
     * @param model used to check and interact with the model.
     */
    public PartyController(VCHandler handler, Model model) {
        this.handler = handler;
        this.model = model;
    }

    @Override
    public void update() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }
    }
}
