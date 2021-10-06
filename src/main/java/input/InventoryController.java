package input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import model.Model;
import run.Boot;
import view.CombatScreen;

public class InventoryController implements IController {
    private Model model;
    private final Boot game;

    public InventoryController(Boot game, Model model) {
        this.game = game;
        this.model = model;
    }

    @Override
    public void update() {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            System.out.println("ESCAPE");
            game.setScreen(new CombatScreen(game, model));
            game.controller.switchController(InputController.Controllers.COMBAT);
        }
    }
}
