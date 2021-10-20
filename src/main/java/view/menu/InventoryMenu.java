package view.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import input.IMenuController;
import model.attack.Attack;
import model.inventories.Inventory;
import model.inventories.Item;
import view.screenObjects.Image;
import view.screenObjects.RectangleBorder;
import view.screenObjects.Text;
import view.screenObjects.TextBox;

import java.util.List;

public class InventoryMenu extends Menu {

    private List<Item> inventory;
    private int numDisplayItems;
    private int maxIndex;

    private Text name;
    private TextBox description;

    private int[] menuYpos;

    private Image arrowUp;
    private Image arrowDown;


    /**
     * Constructor for creating InventoryMenu
     * @param batch
     * @param controller
     * @param menuItems
     * @param inventory
     * @param numDisplayItems
     * @param ySpacing
     */
    public InventoryMenu(SpriteBatch batch, IMenuController controller, List<MenuItem> menuItems, List<Item> inventory, int numDisplayItems, int ySpacing) {
        super(batch, controller, menuItems);
        BitmapFont nameFont = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"), Gdx.files.internal("fonts/pixelfont.png"), false);
        BitmapFont desFont = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"), Gdx.files.internal("fonts/pixelfont.png"), false);
        this.inventory = inventory;
        System.out.println("inventory size:" + this.inventory.size());
        this.numDisplayItems = numDisplayItems;
        this.maxIndex = numDisplayItems-1;

        this.name = new Text(nameFont, batch,Color.BLACK,520,507, "", 1);
        this.description = new TextBox(desFont, batch,Color.BLACK,520,347,380,140,true, "", 0.75f);

        menuYpos = new int[numDisplayItems];
        getyPos();
        Texture t1 = new Texture(Gdx.files.internal("ArrowUp.png"));
        Texture t2 = new Texture(Gdx.files.internal("ArrowDown.png"));
        arrowUp = new Image(batch,280,535,20,20,t1);
        arrowDown = new Image(batch,280,560-((numDisplayItems+1)*ySpacing),20,20,t2);
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
        if (maxIndex > numDisplayItems-1) arrowUp.render();
        if (maxIndex < inventory.size()-1)arrowDown.render();
        if (index < inventory.size()) {
            name.setMessage(inventory.get(index).getName());
            description.setText(inventory.get(index).getDescription());
            name.render();
            description.render();
        }

        for (int i = 0; i < numDisplayItems; i++) {
            menuItems.get(maxIndex-numDisplayItems+1+i).getRender().setY(menuYpos[i]);
            menuItems.get(maxIndex-numDisplayItems+1+i).getRender().render();
        }


        menuItems.get(menuItems.size()-1).getRender().render();
        batch.end();
        super.update();
    }
}
