package input;

import model.Model;
import run.VCHandler;
import view.IView;

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

    }
}
