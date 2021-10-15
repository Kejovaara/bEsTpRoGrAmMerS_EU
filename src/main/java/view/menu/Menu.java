package view.menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import view.IRender;

import java.util.ArrayList;
import java.util.List;

public class Menu implements IRender {

    private List<MenuItem> menuItems;
    private MenuItem activeItem;
    private SpriteBatch batch;

    public Menu(SpriteBatch batch, List<MenuItem> menuItems, MenuItem activeItem){
        this.batch = batch;
        this.menuItems = menuItems;
        if(menuItems.contains(activeItem)) this.activeItem = activeItem;
        else throw new IllegalArgumentException("Active Item not in menu");
    }

    public Menu(SpriteBatch batch, List<MenuItem> menuItems){
        this(batch, menuItems, menuItems.get(0));
    }

    public Menu(SpriteBatch batch){
        this(batch, new ArrayList<>());
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

        IRender renderObject;
        for (MenuItem menuItem : menuItems) {
            batch.begin();
            if(menuItem == activeItem){
                menuItem.getActiveRender().render();
            }
            else menuItem.getDeactiveRender().render();
            batch.end();
        }

    }
}
