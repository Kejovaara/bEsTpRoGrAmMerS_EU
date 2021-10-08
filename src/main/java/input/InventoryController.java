package input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import model.Model;
import run.Boot;
import view.CombatScreen;
import view.InventoryScreen;

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
            System.out.println("ESCAPE TO COMBAT");
            game.setScreen(new CombatScreen(game, model));
            game.setController(InputController.Controllers.COMBAT);
        }
    }
}
