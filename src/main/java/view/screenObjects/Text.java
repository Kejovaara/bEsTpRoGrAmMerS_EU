package view.screenObjects;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import run.Boot;
import view.IDraw;

import java.util.List;

public class Text implements IDraw {

    private BitmapFont font;
    private final Boot game;
    private int xPos;
    private int yPos;
    private String message;

    public Text(BitmapFont font, Boot game, List<Float> color, int xPos, int yPos, String message, float scale) {
        this.font = font;
        this.game = game;
        this.xPos = xPos;
        this.yPos = yPos;
        this.message = message;

        font.getData().setScale(scale);
        font.setColor(color.get(0), color.get(1), color.get(2),1);
    }

    private void render(){
        font.draw(game.batch, message, xPos, yPos);
    }

    @Override
    public void draw() {
        render();
    }
}
