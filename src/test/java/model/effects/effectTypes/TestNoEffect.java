package model.effects.effectTypes;

import model.effects.IEffect;
import services.puckemonGenerator.CreatePuckemon;
import model.entities.puckemon.IPuckemon;
import model.inventories.Item;
import model.inventories.ItemBuilder;
import org.junit.AfterClass;
import org.junit.Test;
import services.observers.MessageObserver;
import services.observers.MessageHandler;

import static org.junit.Assert.assertTrue;

public class TestNoEffect implements MessageObserver {

//    @BeforeClass
//    public void setAs
//
    private static boolean noEffect = false;
    @Test
    public void testNoEffect() {
        CreatePuckemon createPuckemon = new CreatePuckemon();
        IPuckemon p = createPuckemon.createOwnedPuckemon(1,1);
        IPuckemon enemyP = createPuckemon.createOwnedPuckemon(1,1);

        Item item = ItemBuilder.getRandom();

        MessageHandler.getInstance().addObserver(this);
        IEffect noEffect = new NoEffect(item);

        noEffect.execute(p,enemyP);
    }

    @Override
    public void SetMessage(String message) {
        if(message.contains("You get confused what to do with your")) noEffect = true;
    }

    @AfterClass
    public static void afterNoEffect(){
        assertTrue(noEffect);
    }
}
