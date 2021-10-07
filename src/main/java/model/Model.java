package model;

import model.combat.Combat;
import model.entities.OwnedPuckemon;
import model.entities.Player;
import model.entities.PuckeTrainer;
import model.entities.Puckemon;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private OwnedPuckemon playerPuckemon = new OwnedPuckemon(1, 1);
    private OwnedPuckemon playerPuckemon1 = new OwnedPuckemon(1, 2);
    private OwnedPuckemon playerPuckemon2 = new OwnedPuckemon(1, 3);
    private OwnedPuckemon playerPuckemon3 = new OwnedPuckemon(1, 128);

    private OwnedPuckemon trainerPuckemon = new OwnedPuckemon(1, 3);
    private List<OwnedPuckemon> playerList = new ArrayList<>();
    private List<OwnedPuckemon> trainerList = new ArrayList<>();


    private Player player;
    private PuckeTrainer trainer;
    private Combat combat;

    public Model() {
        playerList.add(playerPuckemon);
        playerList.add(playerPuckemon1);
        playerList.add(playerPuckemon2);
        playerList.add(playerPuckemon3);


        trainerList.add(trainerPuckemon);

        player = new Player(playerList, 10);
        trainer = new PuckeTrainer("Bertil the great", trainerList);

        combat = new Combat(player, trainer);
    }

    public Puckemon getPlayerPuckemon() {
        return player.getPuckemon();
    }

    public Puckemon getTrainerPuckemon() {
        return trainer.getPuckemon();
    }

    public void useAttack(int index){
        combat.usePlayerAttack(index);
    }

    public List<Puckemon> getParty(){
        return player.getParty();
    }
}