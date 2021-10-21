package input;

import model.Model;
import run.VCHandler;
import view.IView;


/**
 * A class that listens to events from a Menu defined in IMenuController. In this implementation
 * the class handles the inputs from the Menu displaying a Puckemons attacks.
 */
public class AttackCombatMenu implements IMenuController{

    private IView view;
    private Model model;
    private final VCHandler handler;

    /**
     * Constructor of AttackCombatMenu
     * @param handler used to switch controller and/or screen.
     * @param model used to check and interact with the model.
     * @param view used to switch between the menus on a Screen.
     */
    public AttackCombatMenu(IView view,Model model, VCHandler handler){
        this.handler = handler;
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
