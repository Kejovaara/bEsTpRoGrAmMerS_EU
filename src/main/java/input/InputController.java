package input;

import run.Boot;
import model.Model;
import run.VCHandler;

/**
 * A class to handle which IController that should be used by the game.
 * @author Rasmus Almryd
 */
public class InputController {
    private final VCHandler handler;
    private Model model;

    private IController activeController;
    private MenuController menuController;
    private CombatController combatController;
    private InventoryController inventoryController;
    private PartyController partyController;
    private VictoryController victoryController;
    private GameOverController gameOverController;

    /**
     * Constructor of InputController
     * @param handler used by the controllers to switch controller and/or screen.
     * @param model used to check and interact with the model.
     */
    public InputController(VCHandler handler, Model model) {
        this.handler = handler;
        this.model = model;
        this.menuController = new MenuController(handler, model);
        this.combatController = new CombatController(handler, model);
        this.partyController = new PartyController(handler, model);
        this.inventoryController = new InventoryController(handler, model);
        this.victoryController = new VictoryController(game,model);
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
     * The different controller states the program can have
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
