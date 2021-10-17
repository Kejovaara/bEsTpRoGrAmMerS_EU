package input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import model.Model;
import run.Boot;
import view.CombatScreen;
import view.IView;
import view.menu.Menu;

public class MainCombatMenu implements IMenuController{

    private IView view;
    private Model model;
    private final Boot game;

    public MainCombatMenu(IView view,Model model, Boot game){
        this.game = game;
        this.view = view;
        this.model = model;
    }


    @Override
    public void onInput(int index) {
        if(index == 0){
            System.out.println("attack");
            view.switchMenu(1);
        }
        else if(index == 1){
            System.out.println("inventory");
        }
        else if(index == 2){
            System.out.println("switch");
        }
        else if(index == 3){
            System.out.println("Flee");
        }
    }
}
