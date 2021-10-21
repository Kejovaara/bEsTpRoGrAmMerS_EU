package input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * Only gives the User the option to exit the game.
 * @author Lukas Jigberg
 */
public class GameOverController implements IController{

    @Override
    public void update() {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }
    }
}