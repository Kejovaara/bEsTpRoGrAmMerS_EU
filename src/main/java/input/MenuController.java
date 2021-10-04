package input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import run.Boot;

public class MenuController implements IController{

    @Override
    public void update(Boot game) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            System.out.println("Left");
        }

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }
    }
}
