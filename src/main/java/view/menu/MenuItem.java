package view.menu;

import view.IRender;

/**
 * A class that represents an item in a Menu according to both rendering and traversing to next elements.
 * @author Rasmus Almryd
 */
public class MenuItem{


    private MenuItem up, down, left, right;

    private final IRender activeRender, deactiveRender;

    private Boolean active;


    /**
     * Constructor of MenuItem. By default, MenuItems are not active.
     * @param activeRender The IRender object that is rendered when MenuItem is the active element in a Menu.
     * @param deactiveRender The IRender object that is rendered when MenuItem is not the active element in a Menu.
     */
    public MenuItem(IRender activeRender, IRender deactiveRender){
        this.activeRender = activeRender;
        this.deactiveRender = deactiveRender;
        this.active = false;

    }

    /**
     * Sets MenuItems active state.
     * @param active The parameter that the active state is set to.
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * Sets the MenuItem that comes after this MenuItem when the up-arrow is pressed.
     * @param up MenuItem that should come next.
     */
    public void setUp(MenuItem up) {
        this.up = up;
    }

    /**
     * Sets the MenuItem that comes after this MenuItem when the down-arrow is pressed.
     * @param down MenuItem that should come next
     */
    public void setDown(MenuItem down) {
        this.down = down;
    }

    /**
     * Sets the MenuItem that comes after this MenuItem when the left-arrow is pressed.
     * @param left MenuItem that should come next.
     */
    public void setLeft(MenuItem left) {
        this.left = left;
    }

    /**
     * Sets the MenuItem that comes after this MenuItem when the right-arrow is pressed.
     * @param right MenuItem that should come next.
     */
    public void setRight(MenuItem right) {
        this.right = right;
    }

    /**
     * Returns the MenuItem that comes after this MenuItem when the up-arrow is pressed and sets it to active.
     * @return The MenuItem that comes next.
     */
    protected MenuItem getUp(){
        if(up == null) return this;
        else {
            this.active = false;
            up.setActive(true);
            return up;
        }
    }

    /**
     * Returns the MenuItem that comes after this MenuItem when the down-arrow is pressed and sets it to active.
     * @return The MenuItem that comes next.
     */
    protected MenuItem getDown(){
        if(down == null) return this;
        else {
            this.active = false;
            down.setActive(true);
            return down;
        }
    }

    /**
     * Returns the MenuItem that comes after this MenuItem when the left-arrow is pressed and sets it to active.
     * @return The MenuItem that comes next.
     */
    protected MenuItem getLeft(){
        if(left == null) return this;
        else {
            this.active = false;
            left.setActive(true);
            return left;
        }
    }

    /**
     * Returns the MenuItem that comes after this MenuItem when the right-arrow is pressed and sets it to active.
     * @return The MenuItem that comes next.
     */
    protected MenuItem getRight(){
        if(right == null) return this;
        else {
            this.active = false;
            right.setActive(true);
            return right;
        }
    }

    /**
     * @return IRender object for when the MenuItem is active.
     */
    protected IRender getActiveRender() {
        return activeRender;
    }

    /**
     * @return IRender object for when the MenuItem is not active.
     */
    protected IRender getDeactiveRender() {
        return deactiveRender;
    }

    /**
     * @return IRender object depending on MenuItem is active or not.
     */
    protected IRender getRender(){
        if (active) return activeRender;
        else return deactiveRender;
    }


}
