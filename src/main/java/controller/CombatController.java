package controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import model.Model;
import run.VCHandler;
import view.screens.Screens;

/**
 * A class to handle input events when the CombatScreen is active.
 * @author Rasmus Almryd
 */
public class CombatController implements IController{

    private final Model model;
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

    /**
     * Checks if player has won or been defeated, if so switches to appropriate screen and controller. If game is ongoing, checks
     * if players active Puckemon has fainted. If so the screen switches to the party screen.
     */
    @Override
    public void update() {
        if(model.getBattleOutcome().equals("Victory")){
            handler.setView(Screens.VICTORY);
            handler.setController(InputController.Controllers.VICTORY);
        }
        if(model.getBattleOutcome().equals("Defeat")){
            handler.setView(Screens.GAME_OVER);
            handler.setController(InputController.Controllers.DEFEAT);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)){
            if(model.getActivePlayerPuckemon().getHealth() <= 0){
                handler.setView(Screens.PARTY);
                handler.setController(InputController.Controllers.PARTY);
            }
        }

    }
}
