package controller;

import model.Model;
import run.VCHandler;
import view.screens.Screens;

/**
 * A class that listens to events from a Menu defined in IMenuController. In this implementation
 * the class handles the inputs from the Menu displaying all inventory items.
 */
public class InventoryMenuController implements IMenuController{
    private final Model model;
    private final VCHandler handler;

    /**
     * Constructor of InventoryMenuController
     * @param handler used to switch controller and/or screen.
     * @param model used to check and interact with the model.
     */
    public InventoryMenuController(Model model, VCHandler handler){
        this.handler = handler;
        this.model = model;
    }

    /**
     * If selected MenuItem was an inventory item then use the item and then
     * go back to the CombatScreen.
     * @param index the index of the selected MenuItem in a Menu.
     */
    @Override
    public void onCursorEnter(int index) {
        if(index < model.getInventory().size()){
            model.useItem(index);
        }
        handler.setView(Screens.COMBAT);
        handler.setController(InputController.Controllers.COMBAT);
    }
}
