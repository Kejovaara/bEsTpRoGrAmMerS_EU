package input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import model.Model;
import run.Boot;
import view.CombatScreen;
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
            return;
        }

        if(model.getPlayerPuckemon().getHealth() >0){
            aliveControlls();
        }else{
            faintedControlls();
        }


    }

    private void aliveControlls(){
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
                    game.setView(new PartyScreen(game, model));
                    game.setController(InputController.Controllers.PARTY);
                }
            }
        }else{
            if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
                if(getScreen().getCursorIndex() == model.getPlayerPuckemon().getMoveSet().size()) getScreen().setMainCombatMenu(true); //Press Back
                else if(model.getAttack(getScreen().getCursorIndex()).getPP() > 0)model.useAttack(getScreen().getCursorIndex()); //Uses attack
            }
        }
    }

    private void faintedControlls(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)){
            game.setView(new PartyScreen(game, model));
            game.setController(InputController.Controllers.PARTY);
        }
    }
}
