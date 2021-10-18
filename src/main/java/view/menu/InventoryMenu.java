package view.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import input.IMenuController;
import model.attack.Attack;
import model.inventories.Inventory;
import view.screenObjects.RectangleBorder;
import view.screenObjects.Text;

import java.util.List;

public class InventoryMenu extends Menu {

    private Inventory inventory;
    private int numDisplayItems;
    private int maxIndex;


    public InventoryMenu(SpriteBatch batch, IMenuController controller, List<MenuItem> menuItems, Inventory inventory, int numDisplayItems) {
        super(batch, controller, menuItems);
        BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"), Gdx.files.internal("fonts/pixelfont.png"), false);
        this.inventory = inventory;
        this.numDisplayItems = numDisplayItems;
        this.maxIndex = numDisplayItems;
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
    public void render() {
        int index = menuItems.indexOf(activeItem);
        for(int i = 0; i < maxIndex && i < inventory.getInventorySize(); i++){
            if (index == maxIndex-i) menuItems.get(maxIndex-numDisplayItems+i).getActiveRender().render();
            else menuItems.get(maxIndex-numDisplayItems+i).getDeactiveRender().render();
        }

    }
}
