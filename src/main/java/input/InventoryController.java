package input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import model.Model;
import run.Boot;
import view.CombatScreen;
import view.InventoryScreen;
import view.PartyScreen;

public class InventoryController implements IController {
    private Model model;
    private final Boot game;

    public InventoryController(Boot game, Model model) {
        this.game = game;
        this.model = model;
    }

    private InventoryScreen getScreen(){
        try {
            return (InventoryScreen) this.game.getActiveScreen();
        }catch (Exception e){
            throw new IllegalArgumentException("Wrong type of screen");
        }
    }

    @Override
    public void update() {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            getScreen().moveUp();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            getScreen().moveDown();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            int index = getScreen().getTargetIndex();
            game.setView(new CombatScreen(game, model));
            game.setController(InputController.Controllers.COMBAT);
            model.useItem(index);
            return;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            System.out.println("ESCAPE TO COMBAT");
            game.setView(new CombatScreen(game, model));
            game.setController(InputController.Controllers.COMBAT);
        }
    }
}
