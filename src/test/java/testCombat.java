import static org.junit.Assert.*;

import model.combat.Combat;
import model.entities.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class testCombat {

    private Combat combat;

    @Before
    public void before(){  //Before each test method
        OwnedPuckemon playerPuckemon = new OwnedPuckemon(1, 1);
        OwnedPuckemon playerPuckemon1 = new OwnedPuckemon(1, 2);
        OwnedPuckemon playerPuckemon2 = new OwnedPuckemon(50, 3);
        OwnedPuckemon playerPuckemon3 = new OwnedPuckemon(1, 128);

        OwnedPuckemon trainerPuckemon = new OwnedPuckemon(3, 3);
        List<OwnedPuckemon> playerList = new ArrayList<>();
        List<OwnedPuckemon> trainerList = new ArrayList<>();

        Player player;
        PuckeTrainer trainer;

        playerList.add(playerPuckemon);
        playerList.add(playerPuckemon1);
        playerList.add(playerPuckemon2);
        playerList.add(playerPuckemon3);


        trainerList.add(trainerPuckemon);

        player = new Player(playerList, 10);
        trainer = new PuckeTrainer("Bertil the great", trainerList);

        combat = new Combat(player, trainer);
    }


    @Test
    public void testSetNickame() {


        assertTrue(true);  // The logical check
    }

}
