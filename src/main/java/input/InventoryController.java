package input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import model.Model;
import run.VCHandler;

/**
 * A class to handle input events when the InventoryScreen is active.
 * @author Rasmus Almryd
 * @author André Kejovaara
 */
public class InventoryController implements IController {
    private Model model;
    private final VCHandler handler;

    /**
     * Constructor of InventoryController
     * @param handler used to switch controller and/or screen.
     * @param model used to check and interact with the model.
     */
    public InventoryController(VCHandler handler, Model model) {
        this.handler = handler;
        this.model = model;
    }


    @Override
    public void update() {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }
    }
}
