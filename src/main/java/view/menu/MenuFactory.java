package view.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import input.AttackCombatMenu;
import input.InventoryMenuController;
import input.MainCombatMenu;
import input.PartyMenuController;
import model.Model;
import run.Boot;
import view.IRender;
import view.IView;
import view.screenObjects.*;

import java.util.ArrayList;
import java.util.List;

public class MenuFactory {

    public static Menu getMainCombatMenu(Boot game, IView view, Model model){
        List<MenuItem> items = new ArrayList<>();
        BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"), Gdx.files.internal("fonts/pixelfont.png"), false);
        int xPos = 620;
        int yPos = 130;
        int xSpacing = 170;
        int ySpacing = 60;

        MenuItem i1 = new MenuItem(new CursorMenuItem(game.batch, "Attack", xPos, yPos,0.75f,true),                            new CursorMenuItem(game.batch, "Attack", xPos, yPos,0.75f,false));
        MenuItem i2 = new MenuItem(new CursorMenuItem(game.batch, "Inventory", xPos+xSpacing, yPos,0.75f,true),           new CursorMenuItem(game.batch, "Inventory", xPos+xSpacing, yPos,0.75f,false));
        MenuItem i3 = new MenuItem(new CursorMenuItem(game.batch, "Switch", xPos, yPos-ySpacing,0.75f,true),              new CursorMenuItem(game.batch, "Switch", xPos, yPos-ySpacing,0.75f,false));
        MenuItem i4 = new MenuItem(new CursorMenuItem(game.batch, "Flee", xPos+xSpacing, yPos-ySpacing,0.75f,true),  new CursorMenuItem(game.batch, "Flee", xPos+xSpacing, yPos-ySpacing,0.75f,false));

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
        return new Menu(game.batch, new MainCombatMenu(view, model, game), items);
    }

    public static Menu getAttackCombatMenu(Boot game, IView view, Model model){


        int x = 100;
        int y = 130;
        int xSpacing = 190;
        int ySpacing = 60;
        List<MenuItem> items = new ArrayList<>();



        IRender tempActive, tempDeactive;
        for (int i = 0; i < model.getAttacks().size(); i++) {
            tempActive = new AttackMenuItem(game.batch, model.getAttack(i), x+(i%2*xSpacing), y-(i/2*ySpacing), 0.75f,true);
            tempDeactive =  new AttackMenuItem(game.batch, model.getAttack(i), x+(i%2*xSpacing), y-(i/2*ySpacing), 0.75f,false);
            items.add(new MenuItem(tempActive,tempDeactive));
        }
        items.add(new MenuItem(new CursorMenuItem(game.batch, "Back", x+420, y-(ySpacing), 0.75f,true), new CursorMenuItem(game.batch, "Back", x+420, y-(ySpacing), 0.75f,false)));

        System.out.println();
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


        return new AttackMenu(game.batch, new AttackCombatMenu(view, model, game), items, model.getAttacks());
    }

    public static Menu getInventoryMenu(Boot game, IView view, Model model){
        int x = 100;
        int y = 480;
        int ySpacing = 60;
        List<MenuItem> items = new ArrayList<>();

        IRender tempActive, tempDeactive;
        for (int i = 0; i < model.getInventory().size(); i++) {
            tempActive = new InventoryMenuItem(game.batch, model.getInventory().get(i), x, y-(i*ySpacing), 0.75f,true);
            tempDeactive =  new InventoryMenuItem(game.batch, model.getInventory().get(i), x, y-(i*ySpacing), 0.75f,false);
            items.add(new MenuItem(tempActive,tempDeactive));
        }

        items.add(new MenuItem(new CursorMenuItem(game.batch,"Back", 700, 70, 0.75f, true),new CursorMenuItem(game.batch,"Back", 700, 70, 0.75f, false)));

        for (int i = 0; i < items.size(); i++) {
            if(i<items.size()-1) items.get(i).setDown(items.get(i+1));
            if(i>0) items.get(i).setUp(items.get(i-1));
        }

        items.get(0).setUp(items.get(items.size()-1));
        items.get(items.size()-1).setDown(items.get(0));

        return new InventoryMenu(game.batch, new InventoryMenuController(view,model,game), items, model.getInventory(), 6, ySpacing);
    }

    public static Menu getPartyMenu(Boot game, IView view, Model model){
        int x = 430;
        int y = 610;
        int ySpacing = 103;
        List<MenuItem> items = new ArrayList<>();


        IRender tempActive, tempDeactive;
        if(model.getParty().size() > 0){
            tempActive = new MainPartyItem(game.batch, model.getParty().get(0),40, y-200, 0.75f, true);
            tempDeactive = new MainPartyItem(game.batch, model.getParty().get(0),40, y-200, 0.75f, false);
            items.add(new MenuItem(tempActive,tempDeactive));
        }


        for (int i = 1; i < model.getParty().size(); i++) {
            tempActive = new NormalPartyItem(game.batch, model.getParty().get(i),x, y-(ySpacing*i), 0.75f, true);
            tempDeactive = new NormalPartyItem(game.batch, model.getParty().get(i),x, y-(ySpacing*i), 0.75f, false);
            items.add(new MenuItem(tempActive,tempDeactive));
        }

        items.add(new MenuItem(new CursorMenuItem(game.batch,"Back", 750, 70, 0.75f, true),new CursorMenuItem(game.batch,"Back", 750, 70, 0.75f, false)));

        for (int i = 0; i < items.size(); i++) {
            if(i != items.size()-1) items.get(i).setDown(items.get(i+1));
            if(i != 0) items.get(i).setUp(items.get(i-1));
            if(i > 0) items.get(i).setLeft(items.get(0));
        }

        if(items.size() > 1){
            items.get(0).setRight(items.get(1));
        }



        return new PartyMenu(game.batch, new PartyMenuController(view,model, game),items, model.getParty());
    }
}
