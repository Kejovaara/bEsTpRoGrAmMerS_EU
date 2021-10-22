package model.combat;

import model.PTypes;
import model.PartyBuilder;
import model.attack.Attack;
import model.effects.IEffect;
import model.effects.effectTypes.HealAmount;
import model.effects.effectTypes.HealPercentage;
import model.entities.*;
import model.entities.puckemon.FixedPuckemon;
import model.entities.puckemon.OwnedPuckemon;
import model.inventories.Item;
import model.inventories.ItemBuilder;
import org.junit.Test;

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
}
