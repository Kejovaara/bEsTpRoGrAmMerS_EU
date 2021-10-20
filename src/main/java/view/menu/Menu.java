package view.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import input.IMenuController;
import view.IRender;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that renders and controls MenuItem and other IRender objects
 * @author Rasmus Almryd
 */
public class Menu implements IRender {

    protected List<MenuItem> menuItems;
    protected MenuItem activeItem;
    protected SpriteBatch batch;
    private IMenuController controller;
    protected int xPos, yPos;

    /**
     *
     * @param batch This is used to display MenuItems and other IRender objects.
     * @param controller This is The IMenuController that handles the input events that Menu creates.
     * @param menuItems The MenuItems that make up the Menu.
     * @param activeItem The MenuItem that should start as active.
     * @param xPos The x-origin for the menu.
     * @param yPos The y-origin for the menu.
     */
    public Menu(SpriteBatch batch,IMenuController controller, List<MenuItem> menuItems, MenuItem activeItem, int xPos, int yPos){
        this.batch = batch;
        this.menuItems = menuItems;
        this.controller = controller;
        if(menuItems.contains(activeItem)) this.activeItem = activeItem;
        else throw new IllegalArgumentException("Active Item not in menu");
        activeItem.setActive(true);
        this.xPos = xPos;
        this.yPos = yPos;
    }

    /**
     *
     * @param batch This is used to display MenuItems and other IRender objects.
     * @param controller This is The IMenuController that handles the input events that Menu creates.
     * @param menuItems The MenuItems that make up the Menu.
     */
    public Menu(SpriteBatch batch,IMenuController controller, List<MenuItem> menuItems){
         this(batch,controller, menuItems, menuItems.get(0), 0,0);

    }

    /**
     *
     * @param batch This is used to display MenuItems and other IRender objects.
     * @param controller This is The IMenuController that handles the input events that Menu creates.
     */
    public Menu(SpriteBatch batch,IMenuController controller){
        this(batch,controller, new ArrayList<>());
    }

    /**
     * Add MenuItem to the menu
     * @param menuItem Item to be added.
     */
    public void addMenuItem(MenuItem menuItem){
        menuItems.add(menuItem);
    }
    /**
     * Remove MenuItem from the menu
     * @param menuItem Item to be removed.
     */
    public void removeMenuItem(MenuItem menuItem){
        menuItems.remove(menuItem);
    }

    /**
     * Sets the new active MenuItem to the object that the current MenuItem has as its up.
     */
    protected void up(){
        activeItem = activeItem.getUp();
    }

    /**
     * Sets the new active MenuItem to the object that the current MenuItem has as its down.
     */
    protected void down(){
        activeItem = activeItem.getDown();
    }

    /**
     * Sets the new active MenuItem to the object that the current MenuItem has as its left.
     */
    protected void left(){
        activeItem = activeItem.getLeft();
    }

    /**
     * Sets the new active MenuItem to the object that the current MenuItem has as its up.
     */
    protected void right(){
        activeItem = activeItem.getRight();
    }

    /**
     * Method that renders all MenuItems and IRender objects that make up the class
     */
    @Override
    public void render() {
        update();
        IRender renderObject;

        for (MenuItem menuItem : menuItems) {
            batch.begin();
            menuItem.getRender().render();
            batch.end();
        }

    }

    /**
     * Method that checks if there is any keypresses
     */
    protected void update(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            up();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            down();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            left();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            right();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            controller.onCursorEnter(menuItems.indexOf(activeItem));
        }
    }

    /**
     * translates all MenuItems on x
     * @param xDelta Amount to translate objects in x
     */
    protected void translateX(int xDelta){
        for(MenuItem menuItem: menuItems){
            menuItem.getActiveRender().setX(menuItem.getActiveRender().getX()+xDelta);
            menuItem.getDeactiveRender().setX(menuItem.getDeactiveRender().getX()+xDelta);
        }
    }

    /**
     * translates all MenuItems on y
     * @param yDelta Amount to translate objects in y
     */
    protected void translateY(int yDelta){
        for(MenuItem menuItem: menuItems){
            menuItem.getActiveRender().setY(menuItem.getActiveRender().getY()+yDelta);
            menuItem.getDeactiveRender().setY(menuItem.getDeactiveRender().getY()+yDelta);
        }
    }

    @Override
    public void setX(int x) {
        translateX(x-this.xPos);
        this.xPos= x;
    }

    @Override
    public void setY(int y) {
        translateY(y-this.yPos);
        this.yPos = y;
    }

    @Override
    public int getX() {
        return xPos;
    }

    @Override
    public int getY() {
        return yPos;
    }
}
