package model.effects.effectTypes;

import model.effects.IEffect;
import model.entities.IPuckemon;
import model.inventories.Item;

public class NoEffect implements IEffect {
    String error;
    Item item;

    public NoEffect(Item item){
        this.item = item;
        error = "" + this.item.getName() + " does not have an effect" + " \n";
    }

    @Override
    public void execute(IPuckemon attackUser, IPuckemon opponent) {
        System.out.println("" + error);
        System.out.print("You get confused what to do with your " + item.getName() + "..." + "\n");
        System.out.print("In despair, you toss your " + item.getName() + " at " + opponent.getName() + ", " + "\n");
        System.out.println("which has absolutely no effect at all.." + "\n");
    }
}
