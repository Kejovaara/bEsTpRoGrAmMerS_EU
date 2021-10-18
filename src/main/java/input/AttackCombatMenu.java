package input;

import model.Model;
import model.attack.Attack;
import run.Boot;
import view.IView;

import java.util.List;

public class AttackCombatMenu implements IMenuController{

    private IView view;
    private Model model;
    private final Boot game;

    public AttackCombatMenu(IView view,Model model, Boot game){
        this.game = game;
        this.view = view;
        this.model = model;
    }

    @Override
    public void onCursorMove(int index) {

    }

    @Override
    public void onCursorEnter(int index) {
        if(index < model.getAttacks().size()){
            model.useAttack(index);
            System.out.println("attack: " + index);
            view.switchMenu(0);
        }else{
            view.switchMenu(0);
            System.out.println("back");
        }

    }
}
