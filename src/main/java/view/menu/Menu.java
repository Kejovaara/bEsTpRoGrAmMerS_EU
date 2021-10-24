package view.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import controller.IMenuController;
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
    protected int xPos, yPos;
    private List<IMenuController> menuControllers = new ArrayList<>();

    /**
     * Constructor for Menu
     * @param batch used to display MenuItems and other IRender objects.
     * @param menuItems The MenuItems that make up the Menu.
     * @param activeItem The MenuItem that should start as active.
     * @param xPos The x-origin for the menu.
     * @param yPos The y-origin for the menu.
     */
    public Menu(SpriteBatch batch, List<MenuItem> menuItems, MenuItem activeItem, int xPos, int yPos){
        this.batch = batch;
        this.menuItems = menuItems;
        if(menuItems.contains(activeItem)) this.activeItem = activeItem;
        else throw new IllegalArgumentException("Active Item not in menu");
        activeItem.setActive(true);
        this.xPos = xPos;
        this.yPos = yPos;
    }

    /**
     * Constructor for Menu.
     * @param batch used to display MenuItems and other IRender objects.
     * @param menuItems The MenuItems that make up the Menu.
     */
    public Menu(SpriteBatch batch, List<MenuItem> menuItems){
         this(batch, menuItems, menuItems.get(0), 0,0);

    }

    /**
     * Constructor for Menu.
     * @param batch used to display MenuItems and other IRender objects.
     */
    public Menu(SpriteBatch batch){
        this(batch, new ArrayList<>());
    }

    /**
     * add a IMenuController to listen on onCursorEnter event.
     * @param controller controller to be added.
     */
    public void addMenuController(IMenuController controller){
        menuControllers.add(controller);
    }

    /**
     * Remove controller from menu.
     * @param controller controller to be added.
     */
    public void removeMenuController(IMenuController controller){
        menuControllers.remove(controller);
    }

    /**
     * Add MenuItem to the menu.
     * @param menuItem Item to be added.
     */
    public void addMenuItem(MenuItem menuItem){
        menuItems.add(menuItem);
    }
    /**
     * Remove MenuItem from the menu.
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
            for(IMenuController menuController: menuControllers){
                menuController.onCursorEnter(menuItems.indexOf(activeItem));
            }
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

    /**
     * Sets menus x-origin and translates all MenuItems by te delta of the new and the old position.
     * @param x X position
     */
    @Override
    public void setX(int x) {
        translateX(x-this.xPos);
        this.xPos= x;
    }

    /**
     * Sets menus y-origin and translates all MenuItems by te delta of the new and the old position.
     * @param y Y position
     */
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
