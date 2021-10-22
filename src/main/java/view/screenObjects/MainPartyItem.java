package view.screenObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import model.entities.puckemon.Puckemon;
import view.IRender;

/**
 * Screen object that renders Main party item on the screen, the active puckemon.
 */

public class MainPartyItem  implements IRender {

    private int xPos, yPos;
    private HealthBar healthBar;
    private Text name, level, hp;
    private Image image;
    private RectangleBorder background;

    private SpriteBatch batch;
    private boolean active;

    private Color aliveColor = new Color(138/255f, 206/255f, 227/255f,1);
    private Color faintedColor = new Color(205/255f, 97/255f, 97/255f,1);

    private BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"), Gdx.files.internal("fonts/pixelfont.png"), false);

    /**
     * Constructor for MainPartyItem
     * @param batch the batch
     * @param puckemon the puckemon
     * @param xPos the x-position where to print
     * @param yPos the y-position where to print
     * @param scale the scale of the main party item
     * @param active whether the main party item menu is active or not
     */
    public MainPartyItem(SpriteBatch batch, Puckemon puckemon, int xPos, int yPos, float scale, Boolean active){
        this.batch = batch;
        this.active = active;
        this.xPos = xPos;
        this.yPos = yPos;

        Color bgColor;
        if(puckemon.getHealth() <= 0){
            bgColor = faintedColor;
        }else{
            bgColor =aliveColor;
        }

        float dx = 0.1f;
        if(!active) bgColor = new Color(bgColor.r-dx,bgColor.g-dx,bgColor.b-dx,1);

        background = new RectangleBorder(xPos,yPos,320,157,Color.BLACK, bgColor,6);

        Texture puckeTexture = new Texture(Gdx.files.internal("front/" + puckemon.getId() + ".png"));
        image = new Image(batch, xPos, yPos, 100,100,puckeTexture);

        healthBar = new HealthBar(xPos,yPos,251,22,puckemon.getMaxHealth(),puckemon.getHealth());

        hp = new Text(font, batch, Color.BLACK, xPos,yPos,puckemon.getMaxHealth()+"/"+puckemon.getHealth(), scale);
        level = new Text(font, batch, Color.BLACK, xPos,yPos,"LV. "+puckemon.getLevel(), scale);
        name = new Text(font, batch, Color.BLACK, xPos,yPos,puckemon.getName(), scale);

        setX(xPos);
        setY(yPos);
    }
    @Override
    public void render() {
        batch.end();
        background.render();
        healthBar.render();
        batch.begin();
        image.render();
        hp.render();
        level.render();
        name.render();
    }

    @Override
    public void setX(int x) {
        background.setX(x);
        image.setX(x+10);
        healthBar.setX(x+30);
        hp.setX(x+220);
        name.setX(x+120);
        level.setX(x+120);
    }

    @Override
    public void setY(int y) {
        background.setY(y);
        image.setY(y+50);
        healthBar.setY(y+40);
        hp.setY(y+35);
        name.setY(y+130);
        level.setY(y+100);
    }

    @Override
    public int getX() {
        return getX();
    }

    @Override
    public int getY() {
        return getY();
    }
}
