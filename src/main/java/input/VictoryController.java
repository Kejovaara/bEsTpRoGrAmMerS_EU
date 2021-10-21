package input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import run.Boot;
import run.VCHandler;
import view.CombatScreen;
import model.Model;
import view.Screens;

public class VictoryController implements IController{
    private Model model;
    private final VCHandler handler;

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