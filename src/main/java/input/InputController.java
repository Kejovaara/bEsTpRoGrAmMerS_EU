package input;

import run.Boot;
import model.Model;

public class InputController {

    private final Boot game;
    private Model model;

    private IController activeController;
    private MenuController menuController;
    private CombatController combatController;
    private InventoryController inventoryController;


    public InputController(Boot game, Model model) {
        this.game = game;
        this.model = model;
        this.menuController = new MenuController(game, model);
        this.combatController = new CombatController(game, model);
        this.inventoryController = new InventoryController(game, model);
    }


    public void switchController(Controllers controllerEnum){
        switch (controllerEnum){
            case MAIN_MENU:
                activeController = menuController;
                break;
            case COMBAT:
                activeController = combatController;
                break;
            case INVENTORY:
                activeController = inventoryController;
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
        INVENTORY,
    }
}
