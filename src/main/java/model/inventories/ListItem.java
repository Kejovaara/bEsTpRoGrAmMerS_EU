package model.inventories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class ListItem {
    Image itemImage;
    private Label itemLabel;
    private Label itemAmount;
    private int y;


    public ListItem(Item item, Label.LabelStyle labelStyle, int y){

        this.itemImage = new Image(new Texture(Gdx.files.internal("items/" + item.getId() + ".png")));
        itemImage.setSize(32,32);
        itemImage.setPosition(100,y);

        this.itemLabel = new Label(item.getName(),labelStyle);
        itemLabel.setSize(235,22);
        itemLabel.setPosition(160,y);

        this.itemAmount = new Label("" + item.getQuantity(), labelStyle);
        itemAmount.setSize(45,22);
        itemAmount.setPosition(435,y);


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
}
