package run;

import input.InputController;
import view.Screens;

public interface VCHandler {
    void setView(Screens screen);
    void setController(InputController.Controllers controller);
}
