package view.screenObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import model.inventories.Item;
import view.IRender;
import view.menu.MenuItem;

public class InventoryMenuItem implements IRender {

    private SpriteBatch batch;
    private  Text text;
    private Image arrow;
    private Boolean active;
    private int xPos,yPos;
    private float scale;

    private Image background;
    private Image itemImg;
    private Text name;
    private Text amount;

    private BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"), Gdx.files.internal("fonts/pixelfont.png"), false);

    public InventoryMenuItem(SpriteBatch batch, Item item, int xPos, int yPos, float scale, Boolean active){
        this.batch = batch;
        this.active = active;
        this.xPos = xPos;
        this.yPos = yPos;
        this.scale = scale;

        Texture texture;
        if (active) texture = new Texture(Gdx.files.internal("listitem/listitem_bg_active.png"));
        else texture = new Texture(Gdx.files.internal("listitem/listitem_bg.png"));
        this.background = new Image(batch,xPos,yPos,376,47, texture);

        Texture itemTexture = new Texture(Gdx.files.internal("items/" + item.getId() + ".png"));
        itemImg = new Image(batch,xPos,yPos, 40,40,itemTexture);

        this.name = new Text(font, batch, Color.BLACK,xPos,yPos, item.getName(), 0.75f);
        this.amount = new Text(font, batch, Color.BLACK,xPos,yPos, item.getQuantity()+"", 0.75f);
        setX(xPos);
        setY(yPos);
    }

    @Override
    public void render() {
        background.render();
        itemImg.render();
        name.render();
        amount.render();
    }

    @Override
    public void setX(int x) {
        background.setX(x);
        itemImg.setX(x+5);
        name.setX(x+50);
        amount.setX(x+335);
    }

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
