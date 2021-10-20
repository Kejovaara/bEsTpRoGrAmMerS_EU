package view.animation;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.StringBuilder;

public class TextAnimation implements Animable{
    private int animationTick = 3;
    private StringBuilder sb = new StringBuilder();
    private String text;
    private Label label;
    private int x,y;
    private int width,height;
    private int index;
    private Stage stage = new Stage();

    private BitmapFont font;
    private Label.LabelStyle fontStyle = new Label.LabelStyle();


    public TextAnimation(Label label, String text){
        this.label = label;
        this.text = text;
    }


    public void setMessage(String message){
        if(text.equals(message) && !isDone()){
            System.out.println("SAME MESSAGE");
            return;
        }
        System.out.println(message);
        sb.clear();
        index = 0;
        text = message;
    }

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
