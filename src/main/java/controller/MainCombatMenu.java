package controller;

import model.Model;
import run.VCHandler;
import view.IView;
import view.screens.Screens;

/**
 * A class that listens to events from a Menu defined in IMenuController. In this implementation
 * the class handles the inputs from the Menu with the options Attack, Inventory, Switch and Flee.
 * @author Rasmus Almryd
 */
public class MainCombatMenu implements IMenuController{

    private final IView view;
    private final Model model;
    private final VCHandler handler;

    /**
     * Constructor of MainCombatMenu
     * @param handler used to switch controller and/or screen.
     * @param model used to check and interact with the model.
     * @param view used to switch between the menus on a Screen.
     */
    public MainCombatMenu(IView view, Model model, VCHandler handler){
        this.handler = handler;
        this.view = view;
        this.model = model;
    }

    /**
     * Depending on the selected MenuItem, switch to a particular view/menu or
     * do an action.
     * @param index the index of the selected MenuItem in a Menu.
     */
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
            model.useFlee();
        }
    }
}
