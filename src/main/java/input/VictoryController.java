package input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import run.VCHandler;
import model.Model;
import view.Screens;

/**
 * Gives the User 3 new options after victory.
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

    /**
     * 3 Options for the user when they have won the game. Creates a new battle with one out of the 3 different combatant.
     * FixedPuckemon, DumbTrainer and SmartTrainer. Essentially "restarting" the game.
     */
    @Override
    public void update() {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            model.startCombatWildPuckemon(10);
            handler.setView(Screens.COMBAT);
            handler.setController(InputController.Controllers.COMBAT);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            model.startCombatTrainer(3,10,false);
            handler.setView(Screens.COMBAT);
            handler.setController(InputController.Controllers.COMBAT);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            model.startCombatTrainer(6,17,true);
            handler.setView(Screens.COMBAT);
            handler.setController(InputController.Controllers.COMBAT);
        }
    }
}