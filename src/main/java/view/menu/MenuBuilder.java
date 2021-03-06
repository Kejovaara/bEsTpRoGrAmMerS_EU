package view.menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import controller.AttackCombatMenu;
import controller.InventoryMenuController;
import controller.MainCombatMenu;
import controller.PartyMenuController;
import model.Model;
import run.VCHandler;
import view.IRender;
import view.IView;
import view.screenObjects.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A Builder class that helps build Menu objects without reveling to external classes how they are implemented.
 * @author Rasmus Almryd
 */
public class MenuBuilder {

    /**
     * Builder class for Menu.
     * @param batch This is used to display IRender objects.
     * @param handler This is used to switch screens and controllers.
     * @param view  This is used to switch between menus on the same screen.
     * @param model This parameter is used to display game information to menu objects
     * @return Menu.
     */
    public static Menu getMainCombatMenu(SpriteBatch batch, VCHandler handler, IView view, Model model){
        List<MenuItem> items = new ArrayList<>();
        int xPos = 620;
        int yPos = 130;
        int xSpacing = 170;
        int ySpacing = 60;

        //Creates all MenuItems with their corresponding active/deactivate IRender objects
        MenuItem i1 = new MenuItem(new CursorMenuItem(batch, "Attack", xPos, yPos,0.75f,true),                            new CursorMenuItem(batch, "Attack", xPos, yPos,0.75f,false));
        MenuItem i2 = new MenuItem(new CursorMenuItem(batch, "Inventory", xPos+xSpacing, yPos,0.75f,true),           new CursorMenuItem(batch, "Inventory", xPos+xSpacing, yPos,0.75f,false));
        MenuItem i3 = new MenuItem(new CursorMenuItem(batch, "Switch", xPos, yPos-ySpacing,0.75f,true),              new CursorMenuItem(batch, "Switch", xPos, yPos-ySpacing,0.75f,false));
        MenuItem i4 = new MenuItem(new CursorMenuItem(batch, "Flee", xPos+xSpacing, yPos-ySpacing,0.75f,true),  new CursorMenuItem(batch, "Flee", xPos+xSpacing, yPos-ySpacing,0.75f,false));

        //Setting the connections between MenuItems
        i1.setDown(i3);
        i1.setLeft(i4);
        i1.setRight(i2);

        i2.setLeft(i1);
        i2.setRight(i3);
        i2.setDown(i4);

        i3.setRight(i4);
        i3.setLeft(i2);
        i3.setUp(i1);

        i4.setLeft(i3);
        i4.setRight(i1);
        i4.setUp(i2);

        items.add(i1);
        items.add(i2);
        items.add(i3);
        items.add(i4);

        Menu menu = new Menu(batch, items);
        menu.addMenuController(new MainCombatMenu(view, model, handler));
        return menu;
    }

    /**
     * Builder class for AttackMenu
     * @param batch This is used to display IRender objects
     * @param view  This is used to switch between menus on the same screen
     * @param model This parameter is used to display game information to menu objects
     * @return Menu
     */
    public static Menu getAttackCombatMenu(SpriteBatch batch, IView view, Model model){
        int x = 100;
        int y = 130;
        int xSpacing = 190;
        int ySpacing = 60;
        List<MenuItem> items = new ArrayList<>();

        //Creates alla MenuItems with their corresponding active/deactivate IRender objects
        IRender tempActive, tempDeactive;
        for (int i = 0; i < model.getAttacks().size(); i++) {
            tempActive = new AttackMenuItem(batch, model.getAttack(i), x+(i%2*xSpacing), y-(i/2*ySpacing), 0.75f,true);
            tempDeactive =  new AttackMenuItem(batch, model.getAttack(i), x+(i%2*xSpacing), y-(i/2*ySpacing), 0.75f,false);
            items.add(new MenuItem(tempActive,tempDeactive));
        }
        items.add(new MenuItem(new CursorMenuItem(batch, "Back", x+420, y-(ySpacing), 0.75f,true), new CursorMenuItem(batch, "Back", x+420, y-(ySpacing), 0.75f,false)));

        //Setting the connections between MenuItems
        for(int i = 0; i < items.size(); i++){
            if ((i+1) != items.size()) items.get(i).setRight(items.get(i+1));
            else items.get(i).setRight(items.get(0));

            if (i != 0) items.get(i).setLeft(items.get(i-1));
            else items.get(i).setLeft(items.get(items.size()-1));

            if(i/2 >= 1) items.get(i).setUp(items.get(i-2));
            else if(items.size() > 3) items.get(i).setDown(items.get(i+2));
        }

        if(items.size() > 2) {
            items.get(items.size()-1).setUp(items.get(1));
            items.get(1).setRight(items.get(items.size()-1));
        }


        Menu menu = new AttackMenu(batch, items, model.getAttacks());
        menu.addMenuController(new AttackCombatMenu(view, model));
        return menu;
    }

    /**
     * Builder class for InventoryMenu
     * @param batch This is used to display IRender objects
     * @param handler This is used to switch screens and controllers
     * @param model This parameter is used to display game information to menu objects
     * @return Menu
     */
    public static Menu getInventoryMenu(SpriteBatch batch, VCHandler handler, Model model){
        int x = 100;
        int y = 480;
        int ySpacing = 60;
        List<MenuItem> items = new ArrayList<>();

        //Creates alla MenuItems with their corresponding active/deactivate IRender objects
        IRender tempActive, tempDeactive;
        for (int i = 0; i < model.getInventory().size(); i++) {
            tempActive = new InventoryMenuItem(batch, model.getInventory().get(i), x, y-(i*ySpacing),true);
            tempDeactive =  new InventoryMenuItem(batch, model.getInventory().get(i), x, y-(i*ySpacing),false);
            items.add(new MenuItem(tempActive,tempDeactive));
        }
        items.add(new MenuItem(new CursorMenuItem(batch,"Back", 700, 70, 0.75f, true),new CursorMenuItem(batch,"Back", 700, 70, 0.75f, false)));

        //Setting the connections between MenuItems
        for (int i = 0; i < items.size(); i++) {
            if(i<items.size()-1) items.get(i).setDown(items.get(i+1));
            if(i>0) items.get(i).setUp(items.get(i-1));
        }
        items.get(0).setUp(items.get(items.size()-1));
        items.get(items.size()-1).setDown(items.get(0));

        Menu menu = new InventoryMenu(batch, items, model.getInventory(), 6,y, ySpacing);
        menu.addMenuController(new InventoryMenuController(model,handler));
        return menu;
    }

    /**
     * Builder class for PartyMenu
     * @param batch This is used to display IRender objects
     * @param handler This is used to switch screens and controllers
     * @param model This parameter is used to display game information to menu objects
     * @return Menu
     */
    public static Menu getPartyMenu(SpriteBatch batch, VCHandler handler, Model model){
        int x = 430;
        int y = 610;
        int ySpacing = 103;
        List<MenuItem> items = new ArrayList<>();

        //Creates alla MenuItems with their corresponding active/deactivate IRender objects
        IRender tempActive, tempDeactive;
        if(model.getParty().size() > 0){
            tempActive = new MainPartyItem(batch, model.getParty().get(0),40, y-200, 0.75f, true);
            tempDeactive = new MainPartyItem(batch, model.getParty().get(0),40, y-200, 0.75f, false);
            items.add(new MenuItem(tempActive,tempDeactive));
        }
        for (int i = 1; i < model.getParty().size(); i++) {
            tempActive = new NormalPartyItem(batch, model.getParty().get(i),x, y-(ySpacing*i), true);
            tempDeactive = new NormalPartyItem(batch, model.getParty().get(i),x, y-(ySpacing*i), false);
            items.add(new MenuItem(tempActive,tempDeactive));
        }
        items.add(new MenuItem(new CursorMenuItem(batch,"Back", 750, 70, 0.75f, true),new CursorMenuItem(batch,"Back", 750, 70, 0.75f, false)));

        //Setting the connections between MenuItems
        for (int i = 0; i < items.size(); i++) {
            if(i != items.size()-1) items.get(i).setDown(items.get(i+1));
            if(i != 0) items.get(i).setUp(items.get(i-1));
            if(i > 0) items.get(i).setLeft(items.get(0));
        }

        if(items.size() > 1){
            items.get(0).setRight(items.get(1));
        }

        Menu menu = new PartyMenu(batch,items, model.getParty());
        menu.addMenuController(new PartyMenuController(model, handler));
        return menu;
    }
}
