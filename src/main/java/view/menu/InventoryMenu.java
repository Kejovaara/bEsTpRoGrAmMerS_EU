package view.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import input.IMenuController;
import model.attack.Attack;
import model.inventories.Inventory;
import model.inventories.Item;
import view.screenObjects.RectangleBorder;
import view.screenObjects.Text;

import java.util.List;

public class InventoryMenu extends Menu {

    private List<Item> inventory;
    private int numDisplayItems;
    private int maxIndex;

    private Text Name;
    private Text description;

    private int[] menuYpos;



    public InventoryMenu(SpriteBatch batch, IMenuController controller, List<MenuItem> menuItems, List<Item> inventory, int numDisplayItems) {
        super(batch, controller, menuItems);
        BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"), Gdx.files.internal("fonts/pixelfont.png"), false);
        this.inventory = inventory;
        System.out.println("inventory size:" + this.inventory.size());
        this.numDisplayItems = numDisplayItems;
        this.maxIndex = numDisplayItems-1;

        menuYpos = new int[numDisplayItems];
        getyPos();
    }

    private void getyPos(){
        for (int i = 0; i < menuYpos.length; i++) {
            menuYpos[i] = menuItems.get(i).getRender().getY();
        }
    }


    @Override
    public void up() {
        int index1 = menuItems.indexOf(activeItem);
        super.up();
        int index2 = menuItems.indexOf(activeItem);
        if(index2 == menuItems.size()-1 && index1 == 0) maxIndex = index2-1;
        else if(index2 < maxIndex-numDisplayItems+1) maxIndex--;
    }

    @Override
    public void down() {
        int index1 = menuItems.indexOf(activeItem);
        super.down();
        int index2 = menuItems.indexOf(activeItem);
        if(index1 == menuItems.size()-1 && index2 == 0) maxIndex = numDisplayItems-1;
        else if(index2 > maxIndex && index2 != menuItems.size()-1) maxIndex++;

        System.out.println(maxIndex);

    }

    @Override
    public void render() {
        int index = menuItems.indexOf(activeItem);
        batch.begin();
//        for(int i = 0; i < maxIndex && i < inventory.size(); i++){
//            if (index == maxIndex-i) menuItems.get(maxIndex-numDisplayItems+i).getActiveRender().render();
//            else menuItems.get(maxIndex-numDisplayItems+i).getDeactiveRender().render();
//        }

        for (int i = 0; i < numDisplayItems; i++) {
            System.out.println(menuYpos[i]);
            menuItems.get(maxIndex-numDisplayItems+1+i).getRender().setY(menuYpos[i]);
            menuItems.get(maxIndex-numDisplayItems+1+i).getRender().render();
        }


        menuItems.get(menuItems.size()-1).getRender().render();
        batch.end();
        super.update();
    }
}
