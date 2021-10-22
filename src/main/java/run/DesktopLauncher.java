package run;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.foregroundFPS=60;
        config.vSyncEnabled=false;
//        config.setTitle("Puckemon");
        int screenWidth = 960;
        int screeHeight = 640;

        config.height = screeHeight;
        config.width = screenWidth;
        config.resizable = false;
//        config.setWindowedMode(screenWidth, screeHeight);
//        config.setResizable(false);
        //config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
        new LwjglApplication(new Boot(screenWidth, screeHeight), config);
    }
}
