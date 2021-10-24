package view.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import model.attack.Attack;
import view.screenObjects.RectangleBorder;
import view.screenObjects.Text;

import java.util.List;

/**
 * Class that renders and controls MenuItem and other IRender objects. Extends the functionality of the Menu class.
 * In addition, this class displays information about attacks.
 * @author Rasmus Almryd
 */
public class AttackMenu extends Menu{

    private final RectangleBorder background;
    private final RectangleBorder attackInfoBackground;
    private final Text PP;
    private final Text PPAmount;
    private final Text Type;
    private final List<Attack> attacks;

    /**
     * Constructor for AttackMenu
     * @param batch used to display MenuItems and other IRender objects.
     * @param menuItems The MenuItems that make up the Menu.
     * @param attacks list of attacks used to display their stats
     */
    public AttackMenu(SpriteBatch batch, List<MenuItem> menuItems, List<Attack> attacks) {
        super(batch, menuItems);
        BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"), Gdx.files.internal("fonts/pixelfont.png"), false);
        this.PP = new Text(font,batch,Color.BLACK, 710,130,"PP", 0.75f);
        this.PPAmount = new Text(font, batch, Color.BLACK,800, 130," ", 0.75f);
        this.Type = new Text(font, batch, Color.BLACK,710, 70," ", 0.75f);
        this.attacks = attacks;
        this.background = new RectangleBorder(0,0,960, 180, Color.BLACK, Color.WHITE, 8);
        this.attackInfoBackground = new RectangleBorder(660,0,300, 180, Color.BLACK, Color.WHITE, 8);
    }

    /**
     * Renders the menu
     */
    @Override
    public void render() {
        //render menu backgrounds
        background.render();
        attackInfoBackground.render();

        batch.begin();
        //displays stats about attack
        int index = menuItems.indexOf(activeItem);
        if(index < attacks.size()){
            PPAmount.setMessage(attacks.get(index).getPP()+"/" + attacks.get(index).getBasePP());
            Type.setMessage("Type / " + attacks.get(index).getType());
            PPAmount.render();
            PP.render();
            Type.render();
        }
        batch.end();
        super.render();
    }
}
