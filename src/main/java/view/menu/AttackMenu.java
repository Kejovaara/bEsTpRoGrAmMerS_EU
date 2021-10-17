package view.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import input.IMenuController;
import view.screenObjects.RectangleBorder;
import view.screenObjects.Text;

import java.util.List;

public class AttackMenu extends Menu{

    private RectangleBorder background;
    private RectangleBorder attackInfoBackground;
    private Text PP;

    public AttackMenu(SpriteBatch batch, IMenuController controller, List<MenuItem> menuItems) {
        super(batch, controller, menuItems);
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
        super.render();
    }
}
