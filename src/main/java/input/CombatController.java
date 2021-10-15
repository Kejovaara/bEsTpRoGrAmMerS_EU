package input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import model.Model;
import run.Boot;
import view.CombatScreen;
import view.InventoryScreen;
import view.PartyScreen;
import view.menu.Menu;
import view.menu.MenuItem;
import view.screenObjects.Text;

import java.util.ArrayList;
import java.util.List;

public class CombatController implements IController{

    private Model model;
    private final Boot game;

    private Menu mainMenu;

    public CombatController(Boot game, Model model) {
        this.game = game;
        this.model = model;
    }


    @Override
    public void update() {

    }

//    private void faintedControls(){
//        if(Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)){
//            game.setView(new PartyScreen(game, model));
//            game.setController(InputController.Controllers.PARTY);
//        }
//    }
//
//    private void aliveControls(){
//        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
//            getScreen().cursorUP();
//            mainMenu.up();
//        }
//
//        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
//            getScreen().cursorDown();
//            mainMenu.down();
//        }
//
//        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
//            getScreen().cursorLeft();
//            mainMenu.left();
//        }
//
//        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
//            getScreen().cursorRight();
//            mainMenu.right();
//        }
//
//        if(getScreen().isMainCombatMenu()){
//            if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
//                if(getScreen().getCursorIndex() == 0) getScreen().setMainCombatMenu(false); //Press Attack option
//                if(getScreen().getCursorIndex() == 1){ // INVENTORY
//                    game.setView(new InventoryScreen(game, model));
//                    game.setController(InputController.Controllers.INVENTORY);
//                    return;
//                }
//                if(getScreen().getCursorIndex() == 2){ //Press Switch
//                    game.setView(new PartyScreen(game, model));
//                    game.setController(InputController.Controllers.PARTY);
//                }
//            }
//        }else{
//            if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
//                if(getScreen().getCursorIndex() == model.getPlayerPuckemon().getMoveSet().size()) getScreen().setMainCombatMenu(true); //Press Back
//                else if(model.getAttack(getScreen().getCursorIndex()).getPP() > 0)model.useAttack(getScreen().getCursorIndex()); //Uses attack
//            }
//        }
//
//
//        if(Gdx.input.isKeyPressed(Input.Keys.I)){
//            System.out.println("INVENTORY");
//            game.setView(new InventoryScreen(game, model));
//            game.setController(InputController.Controllers.INVENTORY);
//        }
//    }
}
