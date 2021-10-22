package view.screenObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import model.attack.Attack;

/**
 * Screen object that renders AttackMenu items on the screen
 */
public class AttackMenuItem extends CursorMenuItem {

    private Attack attack;

    /**
     * Constructor for the AttackMenuItem
     * @param batch the batch
     * @param attack the attack
     * @param xPos the position where to print
     * @param yPos the position where to print
     * @param scale the scale of the object to print
     * @param active whether the item is active or not
     */
    public AttackMenuItem(SpriteBatch batch, Attack attack, int xPos, int yPos, float scale, Boolean active) {
        super(batch, attack.getName(), xPos, yPos, scale, active);
        this.attack = attack;
    }

    @Override
    public void render() {
        if(attack.getPP() <= 0) {
            super.changeTextColor(Color.GRAY);
        }
        super.render();
    }
}
