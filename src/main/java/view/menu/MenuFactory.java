package view.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import input.AttackCombatMenu;
import input.MainCombatMenu;
import model.Model;
import model.attack.Attack;
import run.Boot;
import view.IRender;
import view.IView;
import view.screenObjects.AttackMenuItem;
import view.screenObjects.CombatMenuItem;
import view.screenObjects.Text;

import java.util.ArrayList;
import java.util.List;

public class MenuFactory {

    public static Menu getMainCombatMenu(Boot game, IView view, Model model){
        List<MenuItem> items = new ArrayList<>();
        BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/pixelfont.fnt"), Gdx.files.internal("fonts/pixelfont.png"), false);

        MenuItem i1 = new MenuItem(new Text(font, game.batch, Color.BLACK, 200, 400,"Attack",0.75f),      new Text(font, game.batch, Color.GRAY, 200, 400,"Attack",0.75f));
        MenuItem i2 = new MenuItem(new Text(font, game.batch, Color.BLACK, 300, 400,"Inventory",0.75f),   new Text(font, game.batch, Color.GRAY, 300, 400,"Inventory",0.75f));
        MenuItem i3 = new MenuItem(new Text(font, game.batch, Color.BLACK, 200, 300,"Switch",0.75f),      new Text(font, game.batch, Color.GRAY, 200, 300,"Switch",0.75f));
        MenuItem i4 = new MenuItem(new Text(font, game.batch, Color.BLACK, 300, 300,"Flee",0.75f),        new Text(font, game.batch, Color.GRAY, 300, 300,"Flee",0.75f));

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
        int y = 400;
        int xSpacing = 190;
        int ySpacing = 60;
        List<MenuItem> items = new ArrayList<>();



        IRender tempActive, tempDeactive;
        for (int i = 0; i < model.getAttacks().size(); i++) {
            tempActive = new AttackMenuItem(game.batch, model.getAttack(i), x+(i%2*xSpacing), y-(i/2*ySpacing), 0.75f,true);
            tempDeactive =  new AttackMenuItem(game.batch, model.getAttack(i), x+(i%2*xSpacing), y-(i/2*ySpacing), 0.75f,false);
            items.add(new MenuItem(tempActive,tempDeactive));
        }
        items.add(new MenuItem(new CombatMenuItem(game.batch, "Back", x+420, y-(ySpacing), 0.75f,true), new CombatMenuItem(game.batch, "Back", x+420, y-(ySpacing), 0.75f,false)));

        for(int i = 0; i < items.size(); i++){
            if ((i+1) != items.size()) items.get(i).setRight(items.get(i+1));
            else items.get(i).setRight(items.get(0));

            if (i != 0) items.get(i).setLeft(items.get(i-1));
            else items.get(i).setLeft(items.get(items.size()-1));

            if(i/2 >= 1) items.get(i).setUp(items.get(i-2));
            else items.get(i).setDown(items.get(i+2));
        }

        if(items.size() > 2) {
            items.get(items.size()-1).setUp(items.get(1));
            items.get(1).setRight(items.get(items.size()-1));
        }


        return new Menu(game.batch, new AttackCombatMenu(view, model, game), items);
    }
}
