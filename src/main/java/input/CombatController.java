package input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import model.Model;
import run.Boot;
import view.CombatScreen;
import view.InventoryScreen;
import view.PartyScreen;

public class CombatController implements IController{

    private Model model;
    private final Boot game;
    private CombatScreen screen;

    public CombatController(Boot game, Model model) {
        this.game = game;
        this.model = model;


    }

    private CombatScreen getScreen(){
        try {
            return (CombatScreen) this.game.getActiveScreen();
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
            getScreen().cursorUP();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            getScreen().cursorDown();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            getScreen().cursorLeft();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            getScreen().cursorRight();
        }

        if(getScreen().isMainCombatMenu()){
            if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
                if(getScreen().getCursorIndex() == 0) getScreen().setMainCombatMenu(false); //Press Attack option
                if(getScreen().getCursorIndex() == 2){ //Press Switch
                    game.setScreen(new PartyScreen(game, model));
                    game.setController(InputController.Controllers.PARTY);
                }
            }
        }else{
            if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
                if(getScreen().getCursorIndex() == model.getParty().size()) getScreen().setMainCombatMenu(true); //Press Back
                else model.useAttack(getScreen().getCursorIndex()); //Uses attack
            }
        }


        if(Gdx.input.isKeyPressed(Input.Keys.I)){
            System.out.println("INVENTORY");
            game.setScreen(new InventoryScreen(game, model));
            game.controller.switchController(InputController.Controllers.INVENTORY);
        }
    }
}