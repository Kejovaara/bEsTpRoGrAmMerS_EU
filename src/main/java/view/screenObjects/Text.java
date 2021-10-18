package view.screenObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import run.Boot;
import view.IDraw;
import view.IRender;

import java.util.List;

public class Text implements IDraw,IRender {

    private BitmapFont font;
    private final SpriteBatch batch;
    private int xPos;
    private int yPos;
    private String message;
    private Color color;

    public Text(BitmapFont font, SpriteBatch batch, Color color, int xPos, int yPos, String message, float scale) {
        this.font = font;
        this.batch = batch;
        this.xPos = xPos;
        this.yPos = yPos;
        this.message = message;
        this.color = color;

        font.getData().setScale(scale);

        //font.setColor(color.get(0), color.get(1), color.get(2),1);
    }

    public void render(){
        font.setColor(color);
        font.draw(batch, message, xPos, yPos);
    }


    @Override
    public void draw() {
        render();
    }
}
