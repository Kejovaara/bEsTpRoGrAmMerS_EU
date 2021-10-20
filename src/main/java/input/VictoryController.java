package input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import run.Boot;
import view.CombatScreen;
import model.Model;
import view.Screens;

public class VictoryController implements IController{
    private Model model;
    private final Boot game;

    public VictoryController(Boot game, Model model) {
        this.game = game;
        this.model = model;
    }

    @Override
    public void update() {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            Gdx.app.exit();
        }
    }
}