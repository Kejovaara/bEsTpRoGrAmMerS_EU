package model.inventories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class ListItem {
    Image itemImage;
    Image listItemBackground;
    private Label itemLabel;
    private Label itemAmount;
    private int y;


    public ListItem(Item item, Label.LabelStyle labelStyle, int y){

        this.itemImage = new Image(new Texture(Gdx.files.internal("items/" + item.getId() + ".png")));
        itemImage.setSize(32,32);
        itemImage.setPosition(100,y);

        this.itemLabel = new Label(item.getName(),labelStyle);
        itemLabel.setSize(235,22);
        itemLabel.setPosition(140,y+4);

        this.itemAmount = new Label("" + item.getQuantity(), labelStyle);
        itemAmount.setSize(45,22);
        itemAmount.setPosition(430,y+4);

        this.y = y;
        setInactive();
    }

    public void setActive(){
        this.listItemBackground = new Image(new Texture(Gdx.files.internal("listitem/listitem_bg_active.png")));
        listItemBackground.setSize(376,47);
        listItemBackground.setPosition(90,y-7);
    }

    public void setInactive(){
        this.listItemBackground = new Image(new Texture(Gdx.files.internal("listitem/listitem_bg.png")));
        listItemBackground.setSize(376,47);
        listItemBackground.setPosition(90,y-7);
    }

    public Label getItemLabel(){
        return itemLabel;
    }

    public Label getItemAmount(){
        return itemAmount;
    }

    public Image getItemImage(){
        return itemImage;
    }

    public Image getListItemBackground(){
        return listItemBackground;
    }
}
