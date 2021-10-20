package input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import model.Model;
import run.Boot;
import run.VCHandler;
import view.CombatScreen;
import view.IView;
import view.InventoryScreen;
import view.Screens;
import view.menu.Menu;

public class MainCombatMenu implements IMenuController{

    private IView view;
    private Model model;
    private final VCHandler handler;

    public MainCombatMenu(IView view, Model model, VCHandler handler){
        this.handler = handler;
        this.view = view;
        this.model = model;
    }


    @Override
    public void onCursorMove(int index) {

    }

    @Override
    public void onCursorEnter(int index) {
        if(index == 0){
            view.switchMenu(1);
        }
        else if(index == 1){
            handler.setView(Screens.INVENTORY);
            handler.setController(InputController.Controllers.INVENTORY);
        }
        else if(index == 2){
            handler.setView(Screens.PARTY);
            handler.setController(InputController.Controllers.PARTY
            );
        }
        else if(index == 3){
            System.out.println("Flee");
        }
    }
}
