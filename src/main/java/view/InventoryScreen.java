package view;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import model.Model;
import run.Boot;

public class InventoryScreen implements Screen {

    final Boot game;
    private Model model;
    private int screenWidth, screenHeight;
    private ShapeRenderer shapeRenderer;

    private BitmapFont inventoryText;

    public InventoryScreen(final Boot game, Model model){
        this.game = game;
        this.model = model;
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
