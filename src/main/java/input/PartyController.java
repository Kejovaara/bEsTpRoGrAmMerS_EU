package input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import model.Model;
import run.Boot;
import view.CombatScreen;
import view.PartyScreen;

public class PartyController implements IController{

    private Model model;
    private final Boot game;
    private PartyScreen screen;

    public PartyController(Boot game, Model model) {
        this.game = game;
        this.model = model;
    }

    private PartyScreen getScreen(){
        try {
            return (PartyScreen) this.game.getActiveScreen();
        }catch (Exception e){
            throw new IllegalArgumentException("Wrong type of screen");
        }
    }

    @Override
    public void update() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE)){
            game.setView(new CombatScreen(game, model));
            game.setController(InputController.Controllers.COMBAT);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            getScreen().moveTargetIndexRightLeft(true);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            getScreen().moveTargetIndexRightLeft(false);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            getScreen().moveTargetIndexUpDown(true);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            getScreen().moveTargetIndexUpDown(false);
        }
    }
}
