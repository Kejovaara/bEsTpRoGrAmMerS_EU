package input;

import model.Model;
import run.VCHandler;
import view.IView;
import view.Screens;

public class PartyMenuController implements IMenuController{

    private IView view;
    private Model model;
    private final VCHandler handler;

    public PartyMenuController(IView view, Model model, VCHandler handler){
        this.handler = handler;
        this.view = view;
        this.model = model;
    }

    @Override
    public void onCursorMove(int index) {

    }

    @Override
    public void onCursorEnter(int index) {
        if(index < model.getParty().size()){
            if(model.getParty().get(index).getHealth() > 0 && index > 1){
                if (model.getPlayerPuckemon().getHealth()>0){
                    model.switchPuckemon(index);
                    model.useSwitch();
                }else{
                    model.switchPuckemon(index);
                }
                handler.setView(Screens.COMBAT);
                handler.setController(InputController.Controllers.COMBAT);
            }
        }else{
            handler.setView(Screens.COMBAT);
            handler.setController(InputController.Controllers.COMBAT);
        }
    }
}
