package input;

import run.Boot;
import model.Model;
import view.PartyScreen;

public class InputController {

    private final Boot game;
    private Model model;

    private IController activeController;
    private MenuController menuController;
    private CombatController combatController;
    private PartyController partyController;


    public InputController(Boot game, Model model) {
        this.game = game;
        this.model = model;
        this.menuController = new MenuController(game, model);
        this.combatController = new CombatController(game, model);
        this.partyController = new PartyController(game, model);
    }


    public void switchController(Controllers controllerEnum){
        switch (controllerEnum){
            case MAIN_MENU:
                activeController = menuController;
                System.out.println("Menu Control");
                break;
            case COMBAT:
                activeController = combatController;
                System.out.println("Combat Control");
                break;
            case PARTY:
                activeController = partyController;
                System.out.println("Party Control");
                break;
            default:
                activeController = null;
                break;
        }
    }

    public void setActiveController(IController controller){
        activeController = controller;
    }

    public void update(){
        if(activeController != null) activeController.update();
    }

    public enum Controllers{
        MAIN_MENU,
        COMBAT,
        PARTY,
    }
}
