package run;

import input.InputController;
import view.Screens;

public interface VCHandler {
    public void setView(Screens screen);
    public void setController(InputController.Controllers controller);
}
