package view.screenObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import model.inventories.Item;
import view.IRender;

/**
 * Screen object that renders Inventory menu items on the screen
 * @author André Kejovaara
 * @author Rasmus Almryd
 */

public class InventoryMenuItem implements IRender {

    private int xPos,yPos;

    private final Image background;
    private final Image itemImg;
    private final Text name;
    private final Text amount;

    /**
     * Constructor for InventoryMenuItem.
     * @param batch the batch.
     * @param item the item.
     * @param xPos the position where to print.
     * @param yPos the position where to print.
     * @param active whether the item is active or not.
     */
    public InventoryMenuItem(SpriteBatch batch, Item item, int xPos, int yPos, Boolean active){
        this.xPos = xPos;
        this.yPos = yPos;

        Texture texture;
        if (active) texture = new Texture(Gdx.files.internal("listitem/listitem_bg_active.png"));
        else texture = new Texture(Gdx.files.internal("listitem/listitem_bg.png"));
        this.background = new Image(batch,xPos,yPos,376,47, texture);

        Texture itemTexture = new Texture(Gdx.files.internal("items/" + item.getId() + ".png"));
        itemImg = new Image(batch,xPos,yPos, 40,40,itemTexture);

        BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"), Gdx.files.internal("fonts/pixelfont.png"), false);
        this.name = new Text(font, batch, Color.BLACK,xPos,yPos, item.getName(), 0.75f);
        this.amount = new Text(font, batch, Color.BLACK,xPos,yPos, item.getQuantity()+"", 0.75f);
        setX(xPos);
        setY(yPos);
    }

    /**
     * Renders the menu item.
     */
    @Override
    public void render() {
        background.render();
        itemImg.render();
        name.render();
        amount.render();
    }

    /**
     * Sets x for all relevant parts of the item.
     * @param x x coordinate.
     */
    @Override
    public void setX(int x) {
        background.setX(x);
        itemImg.setX(x+5);
        name.setX(x+50);
        amount.setX(x+335);
    }

    /**
     * Sets y for all relevant parts of the item.
     * @param y y coordinate.
     */
    @Override
    public void setY(int y) {
        background.setY(y);
        itemImg.setY(y+3);
        name.setY(y+32);
        amount.setY(y+32);
    }

    @Override
    public int getX() {
        return this.xPos;
    }

    @Override
    public int getY() {
        return this.yPos;
    }
}
