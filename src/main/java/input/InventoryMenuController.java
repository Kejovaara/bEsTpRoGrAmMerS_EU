package input;

import model.Model;
import run.VCHandler;
import view.IView;
import view.Screens;

/**
 * A class that listens to events from a Menu defined in IMenuController. In this implementation
 * the class handles the inputs from the Menu displaying all inventory items.
 */
public class InventoryMenuController implements IMenuController{

    private IView view;
    private Model model;
    private final VCHandler handler;

    /**
     * Constructor of InventoryMenuController
     * @param handler used to switch controller and/or screen.
     * @param model used to check and interact with the model.
     * @param view used to switch between the menus on a Screen.
     */
    public InventoryMenuController(IView view, Model model, VCHandler handler){
        this.handler = handler;
        this.view = view;
        this.model = model;
    }

    @Override
    public void onCursorMove(int index) {

    }

    @Override
    public void onCursorEnter(int index) {
        if(index < model.getInventory().size()){
            model.useItem(index);
            handler.setView(Screens.COMBAT);
            handler.setController(InputController.Controllers.COMBAT);
        }else{
            handler.setView(Screens.COMBAT);
            handler.setController(InputController.Controllers.COMBAT);
        }
    }
}
