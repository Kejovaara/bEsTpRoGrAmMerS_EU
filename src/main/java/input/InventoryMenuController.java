package input;

import model.Model;
import run.VCHandler;
import view.IView;
import view.Screens;

public class InventoryMenuController implements IMenuController{

    private IView view;
    private Model model;
    private final VCHandler handler;

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
