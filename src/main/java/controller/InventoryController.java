package controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * A class to handle input events when the InventoryScreen is active.
 * @author Rasmus Almryd
 * @author André Kejovaara
 */
public class InventoryController implements IController {

    /**
     * Exit application when ESCAPE is pressed.
     */
    @Override
    public void update() {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }
    }
}
