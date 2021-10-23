package run;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


/**
 * The main launcher of the game, implements libGDX.
 * @author Emil Jonsson
 * @author Rasmus Almryd
 */
public class DesktopLauncher {

    /**
     * Sets the screen of the game.
     */
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.foregroundFPS=60;
        config.vSyncEnabled=false;
        config.title = "Puckemon";
        int screenWidth = 960;
        int screeHeight = 640;

        config.height = screeHeight;
        config.width = screenWidth;
        config.resizable = false;
//        config.setWindowedMode(screenWidth, screeHeight);
        //config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
        new LwjglApplication(new Boot(screeHeight, screenWidth), config);
    }
}
