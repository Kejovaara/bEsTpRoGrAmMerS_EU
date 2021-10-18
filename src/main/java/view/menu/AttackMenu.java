package view.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import input.IMenuController;
import model.attack.Attack;
import view.screenObjects.RectangleBorder;
import view.screenObjects.Text;

import java.util.List;

public class AttackMenu extends Menu{

    private RectangleBorder background;
    private RectangleBorder attackInfoBackground;
    private Text PP;
    private Text PPAmount;
    private Text Type;
    private List<Attack> attacks;

    public AttackMenu(SpriteBatch batch, IMenuController controller, List<MenuItem> menuItems, List<Attack> attacks) {
        super(batch, controller, menuItems);
        BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"), Gdx.files.internal("fonts/pixelfont.png"), false);
        this.PP = new Text(font,batch,Color.BLACK, 710,130,"PP", 0.75f);
        this.PPAmount = new Text(font, batch, Color.BLACK,800, 130," ", 0.75f);
        this.Type = new Text(font, batch, Color.BLACK,710, 70," ", 0.75f);
        this.attacks = attacks;
        this.background = new RectangleBorder(0,0,960, 180, Color.BLACK, Color.WHITE, 8);
        this.attackInfoBackground = new RectangleBorder(660,0,300, 180, Color.BLACK, Color.WHITE, 8);
    }

    @Override
    public void up() {
        super.up();
    }

    @Override
    public void down() {
        super.down();
    }

    @Override
    public void left() {
        super.left();
    }

    @Override
    public void right() {
        super.right();
    }


    @Override
    public void render() {


        background.render();
        attackInfoBackground.render();
        batch.begin();
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
