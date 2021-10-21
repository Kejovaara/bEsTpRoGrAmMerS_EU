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

/**
 * Class that renders and controls MenuItem and other IRender objects. Extends the functionality of the Menu class and adds a different display style.
 * Instead of showing all items at the same time, the inventoryMenu displays a list of MenuItems. If all items don't fit then some are hidden until
 * they are scrolled to.
 * @author Rasmus Almryd
 */
public class InventoryMenu extends Menu {

    private List<Item> inventory;
    private int numDisplayItems;
    //The maximum inventory item index that should be displayed in the list.
    private int maxIndex;
    private int ySpacing;
    private int startY;

    private Text name;
    private TextBox description;

    private Image arrowUp;
    private Image arrowDown;


    /**
     * Constructor for creating InventoryMenu
     * @param batch used to display MenuItems and other IRender objects.
     * @param controller used to handle the input events that Menu creates.
     * @param menuItems the MenuItems that make up the Menu.
     * @param inventory list of items used for display logic.
     * @param numDisplayItems number of items that should be displayed simultaneously.
     * @param ySpacing how far it is between MenuItems on screen.
     */
    public InventoryMenu(SpriteBatch batch, IMenuController controller, List<MenuItem> menuItems, List<Item> inventory, int numDisplayItems,int startY, int ySpacing) {
        super(batch, controller, menuItems);

        this.inventory = inventory;
        this.numDisplayItems = numDisplayItems;
        this.maxIndex = numDisplayItems-1;
        this.ySpacing = ySpacing;

        BitmapFont nameFont = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"), Gdx.files.internal("fonts/pixelfont.png"), false);
        BitmapFont desFont = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"), Gdx.files.internal("fonts/pixelfont.png"), false);
        this.name = new Text(nameFont, batch,Color.BLACK,520,507, "", 1);
        this.description = new TextBox(desFont, batch,Color.BLACK,520,347,380,140,true, "", 0.75f);

        this.startY = startY;
        Texture t1 = new Texture(Gdx.files.internal("ArrowUp.png"));
        Texture t2 = new Texture(Gdx.files.internal("ArrowDown.png"));
        arrowUp = new Image(batch,280,535,20,20,t1);
        arrowDown = new Image(batch,280,560-((numDisplayItems+1)*ySpacing),20,20,t2);
    }



    @Override
    protected void up() {
        int index1 = menuItems.indexOf(activeItem);
        super.up();
        int index2 = menuItems.indexOf(activeItem);
        //checks if menu was on the first item and then looped around to the last.
        //If that is the case set maxIndex to the index of the last inventory item in the list
        if(index2 == menuItems.size()-1 && index1 == 0) maxIndex = index2-1;
        // otherwise, the maxIndex should just decrease if it has a lower index
        // than the inventory items that are displayed
        else if(index2 < maxIndex-numDisplayItems+1) maxIndex--;
    }

    @Override
    protected void down() {
        int index1 = menuItems.indexOf(activeItem);
        super.down();
        int index2 = menuItems.indexOf(activeItem);
        //checks if menu was on the last item and then looped around to the first.
        //If that is the case set maxIndex to the index of the first inventory item in the list
        if(index1 == menuItems.size()-1 && index2 == 0) maxIndex = numDisplayItems-1;
        // otherwise, the maxIndex should just increase if it has a higher index
        // than the inventory items that are displayed
        else if(index2 > maxIndex && index2 != menuItems.size()-1) maxIndex++;
    }

    @Override
    public void render() {
        int index = menuItems.indexOf(activeItem);

        batch.begin();
        if (maxIndex > numDisplayItems-1) arrowUp.render(); //if there is items above that is not rendered
        if (maxIndex < inventory.size()-1)arrowDown.render(); //if there is items above that is not rendered
        if (index < inventory.size()) {
            name.setMessage(inventory.get(index).getName());
            description.setText(inventory.get(index).getDescription());
            name.render();
            description.render();
        }

        //render items
        for (int i = 0; i < numDisplayItems; i++) {
            menuItems.get(maxIndex-numDisplayItems+1+i).getRender().setY(startY-(i*ySpacing));
            menuItems.get(maxIndex-numDisplayItems+1+i).getRender().render();
        }

        //render back button
        menuItems.get(menuItems.size()-1).getRender().render();
        batch.end();
        super.update();
    }
}
