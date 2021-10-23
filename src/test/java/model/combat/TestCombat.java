package model.combat;

import static org.junit.Assert.*;

import model.PartyBuilder;
import model.PTypes;
import model.effects.IEffect;
import model.effects.effectTypes.HealAmount;
import model.entities.*;
import model.entities.puckemon.FixedPuckemon;
import model.entities.puckemon.OwnedPuckemon;
import model.entities.puckemon.Puckemon;
import model.inventories.Item;
import model.inventories.ItemBuilder;
import model.inventories.Items;
import org.junit.Test;
import services.observers.EffectHandler;
import services.observers.EffectObserver;
import services.puckemonGenerator.CreatePuckemon;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class TestCombat{

    @Test
    public void testCombatUseItem() {
        CreatePuckemon createPuckemon = new CreatePuckemon();
        PartyBuilder partyBuilder = new PartyBuilder();

        List<OwnedPuckemon> pPucks = new ArrayList<>();
        pPucks.add(createPuckemon.createOwnedPuckemon(1,11));

        Player player = new Player(pPucks, 10);


        Item item = ItemBuilder.getItem(Items.MAJOR_HEALING_POTION);

        List<IEffect> effects = new ArrayList<>();
        effects.add(new HealAmount(75));
        Item item2 = new Item(20,"Test Heal","Restores 100 HP",5,1,5,effects);
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

        Item item = ItemBuilder.getItem(Items.MAJOR_HEALING_POTION);
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
    public void testAttackOpponentNoMove(){
        Combat combat;
        CreatePuckemon cp = new CreatePuckemon();

        OwnedPuckemon puck = cp.createOwnedPuckemon(5,100);
        FixedPuckemon weakPuck = cp.createFixedPuckemon(1,5);
        FixedPuckemon strongPuck2 = cp.createFixedPuckemon(5,90);

        List<FixedPuckemon> opList = new ArrayList<>();
        opList.add(weakPuck);
        opList.add(strongPuck2);
        PuckeTrainer opp = new PuckeTrainer("Bertil",opList,true);

        List<OwnedPuckemon> pList = new ArrayList<>();
        pList.add(puck);
        Player p = new Player(pList,10);

        combat = new Combat(p,opp);

        opp.makeMove(p.getActivePuckemon());
        combat.usePlayerAttack(0);

        assertTrue(p.getActivePuckemon().getHealth() == p.getActivePuckemon().getMaxHealth());
    }

    @Test
    public void testCheckVictory(){
        PartyBuilder partyBuilder = new PartyBuilder();
        IFighter opponent = new PuckeTrainer("Bertil",partyBuilder.getRandOpponentTeam(1,1),false);

        Item item = ItemBuilder.getItem(Items.SUPER_KILL_POTION);

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

        Item item = ItemBuilder.getItem(Items.SUPER_KILL_POTION);
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
    public void testSpeed(){
        Combat combat;
        CreatePuckemon cp = new CreatePuckemon();

        OwnedPuckemon puck = cp.createOwnedPuckemon(4,90);
        FixedPuckemon oppPuck = cp.createFixedPuckemon(4,1);


        List<FixedPuckemon> opList = new ArrayList<>();
        opList.add(oppPuck);
        IFighter opp = new PuckeTrainer("Bertil",opList,false);
        List<OwnedPuckemon> pList = new ArrayList<>();
        pList.add(puck);
        Player p = new Player(pList,10);

        combat = new Combat(p,opp);

        combat.usePlayerAttack(0);

        assertEquals(p.getActivePuckemon().getMaxHealth(), p.getActivePuckemon().getHealth());
    }

    @Test
    public void testHighPrio(){
        Combat combat;
        CreatePuckemon cp = new CreatePuckemon();

        OwnedPuckemon puck = cp.createOwnedPuckemon(5,1);
        FixedPuckemon oppPuck = cp.createFixedPuckemon(4,30);

        List<FixedPuckemon> opList = new ArrayList<>();
        opList.add(oppPuck);
        IFighter opp = new PuckeTrainer("Bertil",opList,false);

        List<OwnedPuckemon> pList = new ArrayList<>();
        pList.add(puck);
        Player p = new Player(pList,10);

        combat = new Combat(p,opp);

        combat.usePlayerAttack(0);
        boolean bool = false;
        if(opp.getActivePuckemon().getMaxHealth()!=opp.getActivePuckemon().getHealth()){
            bool = true;
        };

        assertTrue(bool);
    }

    @Test
    public void testFlee(){
        Combat combat;
        CreatePuckemon cp = new CreatePuckemon();
        List<OwnedPuckemon> plist = new ArrayList<>();
        plist.add(cp.createOwnedPuckemon(1,1));

        List<FixedPuckemon> opplist = new ArrayList<>();
        opplist.add(cp.createFixedPuckemon(1,1));

        Player p = new Player(plist,10);
        PuckeTrainer pt = new PuckeTrainer("Bertil",opplist,true);

        combat = new Combat(p,pt);
        combat.useFlee();

        assertTrue(combat.getBattleOutcome() == "Defeat");

    }
}
