import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Boot extends Game {

    private OrthographicCamera camera;
    private SpriteBatch spriteBatch;

    int screenWidth, screenHeight;

    public Boot(int screenHeight, int screenWidth){
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, screenWidth, screenHeight);
        spriteBatch = new SpriteBatch();
    }

    //TODO: learn more about screens https://libgdx.com/dev/simple-game-extended/
    // Maybe there can be a screen for menu, settings, map, and combat
}
