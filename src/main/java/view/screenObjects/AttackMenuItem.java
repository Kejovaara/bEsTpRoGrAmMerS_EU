package view.screenObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import model.attack.Attack;
import view.IRender;

public class AttackMenuItem extends CombatMenuItem {

    private Attack attack;

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
