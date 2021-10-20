package view.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import input.IMenuController;
import model.entities.Puckemon;
import view.screenObjects.Text;
import view.screenObjects.TextBox;

import java.util.List;

public class PartyMenu extends Menu{

    private TextBox infoText;
    private List<? extends Puckemon> puckemons;

    BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"), Gdx.files.internal("fonts/pixelfont.png"), false);

    public PartyMenu(SpriteBatch batch, IMenuController controller, List<MenuItem> menuItems, List<? extends Puckemon> puckemons){
        super(batch,controller, menuItems);
        this.puckemons = puckemons;
        infoText = new TextBox(font, batch, Color.BLACK, 50,125,300, 180, true, "Pick a Puckemon", 0.75f);
    }

    @Override
    public void render() {
        int index = menuItems.indexOf(activeItem);
        if(index < puckemons.size() && puckemons.get(index).getHealth() <= 0){
            infoText.setText("This Puckemon can't battle!");
        }else if(index == 0){
            infoText.setText("This Puckemon is already active");
        }else{
            infoText.setText("Pick a Puckemon");
        }
        batch.begin();
        infoText.render();
        batch.end();
        super.render();
    }
}
