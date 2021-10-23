package model.combat;

import static org.junit.Assert.*;

import model.PartyBuilder;
import model.PTypes;
import model.effects.IEffect;
import model.effects.effectTypes.HealAmount;
import model.entities.*;
import model.entities.puckemon.FixedPuckemon;
import model.entities.puckemon.OwnedPuckemon;
import model.inventories.Item;
import model.inventories.ItemBuilder;
import org.junit.Test;
import services.puckemonGenerator.CreatePuckemon;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class TestCombat {

    @Test
    public void testCombatUseItem() {
        CreatePuckemon createPuckemon = new CreatePuckemon();
        PartyBuilder partyBuilder = new PartyBuilder();

        List<OwnedPuckemon> pPucks = new ArrayList<>();
        pPucks.add(createPuckemon.createOwnedPuckemon(1,11));

        Player player = new Player(pPucks, 10);


        Item item = ItemBuilder.getItem(ItemBuilder.INames.MAJOR_HEALING_POTION);

        List<IEffect> effects = new ArrayList<>();
        effects.add(new HealAmount(75));
        Item item2 = new Item(20,"Test Heal","Restores 100 HP",5,1,5,true,effects);
        player.addItem(item);
        player.addItem(item2);

        List<FixedPuckemon> enemyPucks = new ArrayList<>();
        List<PTypes> types = new ArrayList<>();
        types.add(PTypes.FIRE);
        List<String> attacks = new ArrayList<>();
        attacks.add("Tackle");
        FixedPuckemon ePuck = new FixedPuckemon(10,10,"",types, 50,50,50,50,attacks);
        enemyPucks.add(ePuck);


        PuckeTrainer opponent = new PuckeTrainer("Bertil", enemyPucks , false);

        Combat combat = new Combat(player, opponent);

        combat.useSwitch();
        int healthLostWithoutHPPotion = player.getActivePuckemon().getHealth() - player.getActivePuckemon().getMaxHealth();
        assertTrue(player.getActivePuckemon().getHealth() < player.getActivePuckemon().getMaxHealth());


        combat.usePlayerItem(0);
        assertTrue(player.getActivePuckemon().getHealth() > (player.getActivePuckemon().getMaxHealth()+healthLostWithoutHPPotion*2));

        combat.usePlayerItem(0);
        assertTrue(player.getActivePuckemon().getHealth() == player.getActivePuckemon().getMaxHealth());
    }

    @Test
    public void testItemOpponentNoMove(){
        CreatePuckemon createPuckemon = new CreatePuckemon();

        List<OwnedPuckemon> pPucks = new ArrayList<>();
        pPucks.add(createPuckemon.createOwnedPuckemon(1,100));

        Player player = new Player(pPucks, 10);

        Item item = ItemBuilder.getItem(ItemBuilder.INames.MAJOR_HEALING_POTION);
        player.addItem(item);

        FixedPuckemon p = createPuckemon.createFixedPuckemon(1,1);
        FixedPuckemon strongP = createPuckemon.createFixedPuckemon(5,1);

        List<FixedPuckemon> party = new ArrayList<>();
        party.add(p);
        party.add(strongP);

        PuckeTrainer opponent = new PuckeTrainer("test",party,true);

        Combat combat = new Combat(player, opponent);

        combat.usePlayerItem(0);
        assertTrue(player.getActivePuckemon().getHealth() == player.getActivePuckemon().getMaxHealth());
    }

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
