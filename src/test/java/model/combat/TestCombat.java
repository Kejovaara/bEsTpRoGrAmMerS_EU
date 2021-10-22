package model.combat;

import static org.junit.Assert.*;

import model.PartyBuilder;
import model.entities.*;
import model.entities.puckemon.FixedPuckemon;
import model.entities.puckemon.OwnedPuckemon;
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

        combat.usePlayerAttack(0);
        combat.usePlayerAttack(0);
        combat.usePlayerAttack(0);
        combat.usePlayerAttack(0);
        combat.usePlayerAttack(0);
        combat.usePlayerAttack(0);
        combat.usePlayerAttack(0);

        assertEquals("Defeat",combat.getBattleOutcome());
    }

    @Test
    public void testHighPrio(){
        Combat combat;
        CreatePuckemon cp = new CreatePuckemon();

        OwnedPuckemon puck = cp.createOwnedPuckemon(4,15);
        FixedPuckemon oppPuck = cp.createFixedPuckemon(5,15);

        List<FixedPuckemon> opList = new ArrayList<>();
        opList.add(oppPuck);
        IFighter opp = new PuckeTrainer("Bertil",opList,false);

        List<OwnedPuckemon> pList = new ArrayList<>();
        pList.add(puck);
        Player p = new Player(pList,10);

        combat = new Combat(p,opp);

        combat.usePlayerAttack(0);
    }

    @Test
    public void testLowPrio(){
        Combat combat;
        CreatePuckemon cp = new CreatePuckemon();

        OwnedPuckemon puck = cp.createOwnedPuckemon(5,15);
        FixedPuckemon oppPuck = cp.createFixedPuckemon(4,15);

        List<FixedPuckemon> opList = new ArrayList<>();
        opList.add(oppPuck);
        IFighter opp = new PuckeTrainer("Bertil",opList,false);

        List<OwnedPuckemon> pList = new ArrayList<>();
        pList.add(puck);
        Player p = new Player(pList,10);

        combat = new Combat(p,opp);

        combat.usePlayerAttack(0);
    }

    @Test
    public void testOnlyExecutePlayerAttack(){
        Combat combat;
        CreatePuckemon cp = new CreatePuckemon();

        OwnedPuckemon puck = cp.createOwnedPuckemon(5,15);
        FixedPuckemon oppPuck = cp.createFixedPuckemon(4,15);
        FixedPuckemon oppPuck2 = cp.createFixedPuckemon(4,15);

        List<FixedPuckemon> opList = new ArrayList<>();
        opList.add(oppPuck);
        opList.add(oppPuck2);
        ITrainer opp = new PuckeTrainer("Bertil",opList,false);

        List<OwnedPuckemon> pList = new ArrayList<>();
        pList.add(puck);
        Player p = new Player(pList,10);

        combat = new Combat(p,(IFighter)opp);

        opp.switchPuckemon(1);
        combat.usePlayerAttack(0);
    }


}
