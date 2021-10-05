package input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import model.combat.Combat;
import run.Boot;
import view.CombatScreen;
import model.Main;

public class MenuController implements IController{
    private Main main;
    private final Boot game;

    public MenuController(Boot game, Main main) {
        this.game = game;
        this.main = main;
    }

    @Override
    public void update() {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            System.out.println("ESCAPE");
            game.setScreen(new CombatScreen(game, main));
            game.controller.switchController(InputController.Controllers.COMBAT);
        }
    }
}
