package model.inventories.puckebags;

import model.entities.CreatePuckemon;
import model.entities.puckemon.OwnedPuckemon;
import model.inventories.PlayerPuckeBag;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestPlayerPuckeBag {

    @Test
    public void testAddToParty(){
        List<OwnedPuckemon> list = new ArrayList<>();
        OwnedPuckemon puckemon = new CreatePuckemon().createOwnedPuckemon(5,5);
        list.add(puckemon);
        PlayerPuckeBag playerPuckeBag = new PlayerPuckeBag(list);

        assertEquals(1, playerPuckeBag.getParty().size());  // The logical check
    }

    @Test
    public void testAddToPartyOverflow(){
        List<OwnedPuckemon> list = new ArrayList<>();
        OwnedPuckemon puckemon = new CreatePuckemon().createOwnedPuckemon(5,5);
        list.add(puckemon);
        list.add(puckemon);
        list.add(puckemon);
        list.add(puckemon);
        list.add(puckemon);
        list.add(puckemon);
        list.add(puckemon);
        PlayerPuckeBag playerPuckeBag = new PlayerPuckeBag(list);

        assertEquals(1, playerPuckeBag.getBox().size());  // The logical check
    }

    @Test
    public void testSwitchPuckemon(){
        List<OwnedPuckemon> list = new ArrayList<>();
        OwnedPuckemon puckemon = new CreatePuckemon().createOwnedPuckemon(5,5);
        OwnedPuckemon puckemon1 = new CreatePuckemon().createOwnedPuckemon(3,5);
        list.add(puckemon);
        list.add(puckemon);
        list.add(puckemon1);
        PlayerPuckeBag playerPuckeBag = new PlayerPuckeBag(list);
        playerPuckeBag.switchPuckemon(2);

        assertEquals(3, playerPuckeBag.getActivePuckemon().getId());  // The logical check
    }

    @Test
    public void testEvolvePuckemon(){
        List<OwnedPuckemon> list = new ArrayList<>();
        OwnedPuckemon puckemon = new CreatePuckemon().createOwnedPuckemon(1,15);
        list.add(puckemon);
        PlayerPuckeBag playerPuckeBag = new PlayerPuckeBag(list);
        playerPuckeBag.afterVictory();

        assertEquals(2, playerPuckeBag.getActivePuckemon().getId());  // The logical check
    }

//    @Test
//    public void testGiveOutExp(){
//        List<OwnedPuckemon> list = new ArrayList<>();
//        OwnedPuckemon puckemon = new CreatePuckemon().createOwnedPuckemon(1,6);
//        list.add(puckemon);
//        PlayerBag playerBag = new PlayerBag(list);
//        int expected = (int) Math.pow(puckemon.getLevel(),3) + 1000;
//        playerBag.afterVictory();
//
//
//        assertEquals(expected, playerBag.getActivePuckemon().exp());  // The logical check
//    }

}
