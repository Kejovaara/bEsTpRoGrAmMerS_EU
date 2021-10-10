package model.inventories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class ListItem {
    Texture itemImage;
    private Label itemLabel;
    private Label itemAmount;

    public ListItem(Item item, Label.LabelStyle labelStyle){
        this.itemImage = new Texture(Gdx.files.internal("items/" + item.getId() + ".png"));
        this.itemLabel = new Label(item.getName(),labelStyle);
        this.itemAmount = new Label("" + item.getQuantity(), labelStyle);
    }
}
