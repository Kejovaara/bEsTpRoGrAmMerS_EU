package view.animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.StringBuilder;

/**
 * Class that animates the text in a typing fashion.
 * @author André Kejovaara
 */

public class TextAnimation implements Animable{
    private int animationTick = 3;
    private final StringBuilder sb = new StringBuilder();
    private String text;
    private final Label label;
    private int index;

    /**
     * Constructor for the TextAnimation
     * @param label which label to print to
     * @param text what text is to be printed
     */
    public TextAnimation(Label label, String text){
        this.label = label;
        this.text = text;
    }

    /**
     * Sets the message of an existing text animator
     * @param message the message to display
     */
    public void setMessage(String message){
        if(text.equals(message) && !isDone()){
            return;
        }
        sb.clear();
        index = 0;
        text = message;
    }

    /**
     * Renders the animation.
     * @param batch SpriteBatch to render
     */
    @Override
    public void render(SpriteBatch batch) {
        if(!isDone()){
            animationTick--;
        }
        if(animationTick <= 0 && !isDone()){
            sb.append(text.charAt(index));
            label.setText(sb);
            animationTick = 3;
            index++;
        }
        batch.begin();
            label.draw(batch,1f);
        batch.end();
    }

    @Override
    public boolean isDone() {
        return (text.length()-1 < index);
    }
}
