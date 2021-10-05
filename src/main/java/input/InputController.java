package input;

import run.Boot;
import model.Main;

public class InputController {

    private final Boot game;
    private Main main;

    private IController activeController;
    private MenuController menuController;
    private CombatController combatController;


    public InputController(Boot game, Main main) {
        this.game = game;
        this.main = main;
        this.menuController = new MenuController(game, main);
        this.combatController = new CombatController(game, main);
    }


    public void switchController(Controllers controllerEnum){
        switch (controllerEnum){
            case MAIN_MENU:
                activeController = menuController;
                break;
            case COMBAT:
                activeController = combatController;
                break;
            default:
                activeController = null;
                break;
        }
    }

    public void update(){
        if(activeController != null) activeController.update();
    }

    public enum Controllers{
        MAIN_MENU,
        COMBAT,
    }
}
