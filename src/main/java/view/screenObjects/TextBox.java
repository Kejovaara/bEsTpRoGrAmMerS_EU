package view.screenObjects;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import java.util.List;

/**
 * Labels work different from most libGdx, it isn't rendered, it is added to a Stage.
 */

public class TextBox {

    private final Label label;

    public TextBox(BitmapFont font, List<Float> color, int xPos, int yPos, int width, int height, boolean wrap, String message) {
        LabelStyle fontStyle = new LabelStyle();
        fontStyle.font = font;
        fontStyle.fontColor.set(color.get(0), color.get(1), color.get(2),1);

        label = new Label(message,fontStyle);
        label.setSize(width,height);
        label.setPosition(xPos,yPos);
        label.setWrap(wrap);
    }

    public Label getLabel(){
        return label;
    }
}
