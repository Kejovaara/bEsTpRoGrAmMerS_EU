package input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import model.Model;
import run.Boot;
import view.CombatScreen;
import view.InventoryScreen;

public class CombatController implements IController{

    private Model model;
    private final Boot game;

    public CombatController(Boot game, Model model) {
        this.game = game;
        this.model = model;
    }

    @Override
    public void update() {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.I)){
            System.out.println("INVENTORY");
            game.setScreen(new InventoryScreen(game, model));
            game.controller.switchController(InputController.Controllers.INVENTORY);
        }
    }
}
