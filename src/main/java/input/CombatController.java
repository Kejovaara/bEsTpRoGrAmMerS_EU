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
    private CombatScreen screen;

    private Menu mainMenu;

    public CombatController(Boot game, Model model) {
        this.game = game;
        this.model = model;
        mainMenu = createMainMenu();

    }

    private Menu createMainMenu(){
        List<MenuItem> items = new ArrayList<>();
        BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"), Gdx.files.internal("fonts/pixelfont.png"), false);

        MenuItem i1 = new MenuItem(new Text(font, game, Color.BLACK, 200, 400,"Attack",0.75f),      new Text(font, game, Color.GRAY, 200, 400,"Attack",0.75f));
        MenuItem i2 = new MenuItem(new Text(font, game, Color.BLACK, 300, 400,"Inventory",0.75f),   new Text(font, game, Color.GRAY, 300, 400,"Inventory",0.75f));
        MenuItem i3 = new MenuItem(new Text(font, game, Color.BLACK, 200, 300,"Switch",0.75f),      new Text(font, game, Color.GRAY, 200, 300,"Switch",0.75f));
        MenuItem i4 = new MenuItem(new Text(font, game, Color.BLACK, 300, 300,"Flee",0.75f),        new Text(font, game, Color.GRAY, 300, 300,"Flee",0.75f));

        i1.setDown(i3);
        i1.setLeft(i4);
        i1.setRight(i2);

        i2.setLeft(i1);
        i2.setRight(i3);
        i2.setDown(i4);

        i3.setRight(i4);
        i3.setLeft(i2);
        i3.setUp(i1);

        i4.setLeft(i3);
        i4.setRight(i1);
        i4.setUp(i2);

        items.add(i1);
        items.add(i2);
        items.add(i3);
        items.add(i4);
        return new Menu(game.batch, items);
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
        if(getScreen().testMenu == null)getScreen().testMenu = mainMenu;
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

    private void faintedControlls(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)){
            game.setView(new PartyScreen(game, model));
            game.setController(InputController.Controllers.PARTY);
        }
    }

    private void aliveControlls(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            getScreen().cursorUP();
            mainMenu.up();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            getScreen().cursorDown();
            mainMenu.down();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            getScreen().cursorLeft();
            mainMenu.left();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            getScreen().cursorRight();
            mainMenu.right();
        }

        if(getScreen().isMainCombatMenu()){
            if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
                if(getScreen().getCursorIndex() == 0) getScreen().setMainCombatMenu(false); //Press Attack option
                if(getScreen().getCursorIndex() == 1){ // INVENTORY
                    game.setView(new InventoryScreen(game, model));
                    game.setController(InputController.Controllers.INVENTORY);
                    return;
                }
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


        if(Gdx.input.isKeyPressed(Input.Keys.I)){
            System.out.println("INVENTORY");
            game.setView(new InventoryScreen(game, model));
            game.setController(InputController.Controllers.INVENTORY);
        }
    }
}
