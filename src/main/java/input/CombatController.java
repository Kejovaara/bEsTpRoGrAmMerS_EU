package input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import model.Main;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import run.Boot;

public class CombatController implements IController{

    private Main main;
    private final Boot game;

    public CombatController(Boot game, Main main) {
        this.game = game;
        this.main = main;
    }

    @Override
    public void update() {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }
    }
}
