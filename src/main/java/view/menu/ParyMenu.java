package view.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import input.IMenuController;
import view.screenObjects.Text;
import view.screenObjects.TextBox;

import java.util.List;

public class ParyMenu extends Menu{

    private TextBox infoText;

    BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"), Gdx.files.internal("fonts/pixelfont.png"), false);

    public ParyMenu(SpriteBatch batch, IMenuController controller, List<MenuItem> menuItems){
        super(batch,controller, menuItems);
        infoText = new TextBox(font, batch, Color.BLACK, 50,125,300, 180, true, "Pick a Puckemon", 0.75f);
    }

    @Override
    public void render() {
        batch.begin();
        infoText.render();
        batch.end();
        super.render();
    }
}
