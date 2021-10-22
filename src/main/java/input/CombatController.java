package input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import model.Model;
import run.VCHandler;
import view.Screens;

/**
 * A class to handle input events when the CombatScreen is active.
 * @author Rasmus Almryd
 */
public class CombatController implements IController{

    private Model model;
    private final VCHandler handler;

    /**
     * Constructor of CombatController
     * @param handler used to switch controller and/or screen.
     * @param model used to check and interact with the model.
     */
    public CombatController(VCHandler handler, Model model) {
        this.handler = handler;
        this.model = model;
    }

    @Override
    public void update() {
        if(model.getBattleOutcome()=="Victory"){
            handler.setView(Screens.VICTORY);
            handler.setController(InputController.Controllers.VICTORY);
        }
        if(model.getBattleOutcome()=="Defeat"){
            handler.setView(Screens.GAME_OVER);
            handler.setController(InputController.Controllers.DEFEAT);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)){
            if(model.getPlayerPuckemon().getHealth() <= 0){
                handler.setView(Screens.PARTY);
                handler.setController(InputController.Controllers.PARTY);
            }
        }

    }
}
