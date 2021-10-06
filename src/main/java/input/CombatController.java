package input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import model.Model;
import run.Boot;

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
    }
}
