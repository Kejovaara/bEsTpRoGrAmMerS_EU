package view.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import input.IMenuController;
import view.IRender;

import java.util.ArrayList;
import java.util.List;

public class Menu implements IRender {

    protected List<MenuItem> menuItems;
    protected MenuItem activeItem;
    protected SpriteBatch batch;
    private IMenuController controller;
    protected int xPos, yPos;

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

    public Menu(SpriteBatch batch,IMenuController controller, List<MenuItem> menuItems){
        this(batch,controller, menuItems, menuItems.get(0), 0,0);

    }

    public Menu(SpriteBatch batch,IMenuController controller){
        this(batch,controller, new ArrayList<>());
    }

    public void addMenuItem(MenuItem menuItem){
        menuItems.add(menuItem);
    }

    public void removeMenuItem(MenuItem menuItem){
        menuItems.remove(menuItem);
    }

    public void up(){
        activeItem = activeItem.getUp();
    }
    public void down(){
        activeItem = activeItem.getDown();

    }

    public void left(){
        activeItem = activeItem.getLeft();
    }

    public void right(){
        activeItem = activeItem.getRight();
    }

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

    protected void translateX(int xDelta){
        for(MenuItem menuItem: menuItems){
            menuItem.getActiveRender().setX(menuItem.getActiveRender().getX()+xDelta);
            menuItem.getDeactiveRender().setX(menuItem.getDeactiveRender().getX()+xDelta);
        }
    }

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
        translateX(y-this.yPos);
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
