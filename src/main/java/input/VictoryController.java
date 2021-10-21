package input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import run.VCHandler;
import model.Model;
import view.Screens;

/**
 * Gives the User 2 new options. Combat another easy trainer or try a more difficult opponent.
 * @author Lukas Jigberg
 */
public class VictoryController implements IController{
    private final Model model;
    private final VCHandler handler;

    /**
     * Constructor of VictoryController
     * @param handler used to switch controller and/or screen
     * @param model used to check and interact with the model.
     */
    public VictoryController(VCHandler handler, Model model) {
        this.handler = handler;
        this.model = model;
    }

    @Override
    public void update() {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){

        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            model.startCombat(3,10,false);
            handler.setView(Screens.COMBAT);
            handler.setController(InputController.Controllers.COMBAT);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            model.startCombat(6,17,true);
            handler.setView(Screens.COMBAT);
            handler.setController(InputController.Controllers.COMBAT);
        }
    }
}