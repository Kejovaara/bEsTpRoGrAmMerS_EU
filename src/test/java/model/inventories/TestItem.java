package model.inventories;

import static org.junit.Assert.*;

import model.PartyBuilder;
import model.combat.Combat;
import model.effects.IEffect;
import model.entities.IFighter;
import model.entities.Player;
import model.entities.PuckeTrainer;
import org.junit.Test;

import java.util.List;

public class TestItem {
    @Test
    public void testDescription(){
        Item item = ItemBuilder.getRandom();
        assertTrue(item.getDescription().length() > 0);
    }

    @Test
    public void testGetValue(){
        Item item = ItemBuilder.getRandom();
        assertTrue(item.getValue() > 0);
    }

    @Test
    public void testGetPriority(){
        Item item = ItemBuilder.getItem(Items.ETHER_POTION);
        assertEquals(item.getPriority(), 1);
    }

    @Test
    public void testGetEffects(){
        Item item = ItemBuilder.getItem(Items.SUPER_KILL_POTION);
        List<IEffect> effects = item.getEffects();

        assertTrue(effects.size() > 0);
    }

    @Test
    public void testExecute(){
        PartyBuilder partyBuilder = new PartyBuilder();
        IFighter opponent = new PuckeTrainer("Bertil",partyBuilder.getRandOpponentTeam(1,1),false);

        Item item = ItemBuilder.getItem(Items.SUPER_KILL_POTION);

        Player player = new Player(partyBuilder.getPlayerStartingTeam(),10);
        player.addItem(item);
        player.addItem(item);
        Combat combat = new Combat(player,opponent);

        item.execute(player.getActivePuckemon(),opponent.getActivePuckemon());
        combat.usePlayerItem(0);

        assertTrue(player.getInventory().size() == 0);

        assertTrue(opponent.checkIfDefeated());
    }

    @Test
    public void testWrongEnum(){
        try{
            Item item = ItemBuilder.getItem(null);
        }catch(Exception e){
            System.out.println("WRONG ENUM");
        }

    }

}
