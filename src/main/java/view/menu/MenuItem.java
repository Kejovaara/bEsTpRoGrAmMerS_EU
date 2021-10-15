package view.menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import view.IRender;

public class MenuItem{

    private MenuItem up, down, left, right;

    private IRender activeRender, deactiveRender;


    public MenuItem(IRender activeRender, IRender deactiveRender){
        this.activeRender = activeRender;
        this.deactiveRender = deactiveRender;
    }


    public void setUp(MenuItem up) {
        this.up = up;
    }

    public void setDown(MenuItem down) {
        this.down = down;
    }

    public void setLeft(MenuItem left) {
        this.left = left;
    }

    public void setRight(MenuItem right) {
        this.right = right;
    }

    public MenuItem getUp(){
        if(up == null) return this;
        else return up;
    }

    public MenuItem getDown(){
        if(down == null) return this;
        else return down;
    }

    public MenuItem getLeft(){
        if(left == null) return this;
        else return left;
    }

    public MenuItem getRight(){
        if(right == null) return this;
        else return right;
    }

    public IRender getActiveRender() {
        return activeRender;
    }

    public IRender getDeactiveRender() {
        return deactiveRender;
    }
}
