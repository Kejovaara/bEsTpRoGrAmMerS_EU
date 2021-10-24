package controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * A class to handle input events when the PartyScreen is active.
 * @author Rasmus Almryd
 */
public class PartyController implements IController{

    /**
     * Exit application when ESCAPE is pressed.
     */
    @Override
    public void update() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }
    }
}
