package model.effects.effectTypes;

import model.effects.IEffect;
import model.puckemon.IPuckemon;
import model.inventories.Item;
import serviceControllers.observers.MessageHandler;

/**
 * Class to handle how items with no effect works.
 * @author André Kejovaara
 */
public class NoEffect implements IEffect {
    String error;
    Item item;

    /**
     * Constructor for NoEffect
     * @param item to be used to define that an item has no effect.
     */
    public NoEffect(Item item){
        this.item = item;
        error = "" + this.item.getName() + " does not have an effect" + " \n";
    }

    @Override
    public void execute(IPuckemon attackUser, IPuckemon opponent) {
        MessageHandler.getInstance().DisplayMessage("You get confused what to do with your " + item.getName() + "..." + "\n"
                +"In despair, you toss your " + item.getName() + " at " + opponent.getName() + ", " + "\n"+
                "which has absolutely no effect at all.." + "\n");
    }
}
