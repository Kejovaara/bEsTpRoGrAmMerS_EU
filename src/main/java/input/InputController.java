package input;

import run.Boot;

public class InputController {

    public final Boot game;
    private IController activeController;


    public InputController(Boot game) {
        this.game = game;
    }


    public void switchController(Controllers controllerEnum){
        switch (controllerEnum){
            case MAIN_MENU:
                activeController = new MenuController();
                break;
            case COMBAT:
                activeController = new CombatController();
                break;
            default:
                activeController = null;
                break;
        }
    }

    public void update(){
        if(activeController != null) activeController.update(game);
    }

    public enum Controllers{
        MAIN_MENU,
        COMBAT,
    }
}
