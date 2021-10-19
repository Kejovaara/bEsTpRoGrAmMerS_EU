package view.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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


    public TextAnimation(Label label, String text, int x, int y, int width, int height){
        this.label = label;
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        //FONT STYLING
        font = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"), Gdx.files.internal("fonts/pixelfont.png"), false);
        font.getData().setScale(0.75f);

        // LABEL CONFIG
        label.setPosition(x,y);
        label.setSize(width, height);
        label.setWrap(true);
        stage.addActor(label);
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
        System.out.println(animationTick);
    }

    @Override
    public boolean isDone() {
        return (text.length()-1 < index);
    }
}
