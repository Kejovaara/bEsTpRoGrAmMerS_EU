package controller;

import model.Model;
import run.VCHandler;

/**
 * A class to handle which IController that should be used by the game.
 * @author Rasmus Almryd
 * @author André Kejovaara
 * @author Lukas Jigberg
 * @author Emil Jonsson
 */
public class InputController {

    private IController activeController;
    private final MenuController menuController;
    private final CombatController combatController;
    private final InventoryController inventoryController;
    private final PartyController partyController;
    private final VictoryController victoryController;
    private final GameOverController gameOverController;

    /**
     * Constructor of InputController.
     * @param handler used by the controllers to switch controller and/or screen.
     * @param model used to check and interact with the model.
     */
    public InputController(VCHandler handler, Model model) {
        this.menuController = new MenuController(handler);
        this.combatController = new CombatController(handler, model);
        this.partyController = new PartyController();
        this.inventoryController = new InventoryController();
        this.victoryController = new VictoryController(handler,model);
        this.gameOverController = new GameOverController();
    }

    /**
     * Used to switch controller used by the game.
     * @param controllerEnum the enum of the corresponding controller that should be switched to.
     */
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
            case PARTY:
                activeController = partyController;
                break;
            case VICTORY:
                activeController = victoryController;
                break;
            case DEFEAT:
                activeController = gameOverController;
                break;
            default:
                activeController = null;
                break;
        }
    }

    /**
     * Used to update the controller that is currently active.
     */
    public void update(){
        if(activeController != null) activeController.update();
    }

    /**
     * @return the current active controller.
     */
    public IController getActiveController() {
        return activeController;
    }

    /**
     * The different controller states the program can have.
     */
    public enum Controllers{
        MAIN_MENU,
        COMBAT,
        PARTY,
        INVENTORY,
        VICTORY,
        DEFEAT,
    }
}
