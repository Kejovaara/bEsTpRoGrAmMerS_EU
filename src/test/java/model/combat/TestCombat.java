package model.combat;

import static org.junit.Assert.*;

import model.PartyBuilder;
import model.entities.CreatePuckemon;
import model.entities.IFighter;
import model.entities.Player;
import model.entities.PuckeTrainer;
import model.entities.puckemon.OwnedPuckemon;
import model.entities.puckemon.Puckemon;
import model.inventories.Item;
import model.inventories.ItemBuilder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestCombat {

    @Test
    public void testCheckVictory(){
        PartyBuilder partyBuilder = new PartyBuilder();
        IFighter opponent = new PuckeTrainer("Bertil",partyBuilder.getRandOpponentTeam(1,1),false);

        Item item = ItemBuilder.getItem(ItemBuilder.INames.SUPER_KILL_POTION);

        Player player = new Player(partyBuilder.getPlayerStartingTeam(),10);
        player.addItem(item);
        player.addItem(item);
        Combat combat = new Combat(player,opponent);

        combat.usePlayerItem(0);

        assertEquals(combat.getBattleOutcome(),"Victory");
    }

    @Test
    public void testCheckDefeat(){
        PartyBuilder partyBuilder = new PartyBuilder();
        CreatePuckemon cp = new CreatePuckemon();
        IFighter opponent = new PuckeTrainer("Bertil",partyBuilder.getRandOpponentTeam(1,1),false);
        OwnedPuckemon puck = cp.createOwnedPuckemon(1,1);


        List<OwnedPuckemon> opList = new ArrayList<>();
        opList.add(puck);
        Player p = new Player(opList,10);

        Item item = ItemBuilder.getItem(ItemBuilder.INames.SUPER_KILL_POTION);
        p.addItem(item);

        Combat combat = new Combat(p,opponent);

        opponent.makeMove(opponent.getActivePuckemon());
        opponent.makeMove(opponent.getActivePuckemon());
        opponent.makeMove(opponent.getActivePuckemon());
        opponent.makeMove(opponent.getActivePuckemon());
        opponent.makeMove(opponent.getActivePuckemon());
        opponent.makeMove(opponent.getActivePuckemon());
        opponent.makeMove(opponent.getActivePuckemon());
        opponent.makeMove(opponent.getActivePuckemon());
        opponent.makeMove(opponent.getActivePuckemon());
        opponent.makeMove(opponent.getActivePuckemon());
        opponent.makeMove(opponent.getActivePuckemon());


        assertEquals("Defeat",combat.getBattleOutcome());
    }
}
