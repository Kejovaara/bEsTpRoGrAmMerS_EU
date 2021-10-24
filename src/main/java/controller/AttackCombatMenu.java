package controller;

import model.Model;
import view.IView;

/**
 * A class that listens to events from a Menu defined in IMenuController. In this implementation
 * the class handles the inputs from the Menu displaying a Puckemons attacks.
 * @author Rasmus Almryd
 */
public class AttackCombatMenu implements IMenuController{

    private final IView view;
    private final Model model;

    /**
     * Constructor of AttackCombatMenu
     * @param model used to check and interact with the model.
     * @param view used to switch between the menus on a Screen.
     */
    public AttackCombatMenu(IView view,Model model){
        this.view = view;
        this.model = model;
    }

    /**
     * Checks if selected MenuItem is an attack or the back button and executes
     * either an attack or goes back to previous menu.
     * @param index the index of the selected MenuItem in a Menu.
     */
    @Override
    public void onCursorEnter(int index) {
        if(index < model.getAttacks().size()){
            model.useAttack(index);
            view.switchMenu(0);
        }else{
            view.switchMenu(0);
        }

    }
}
