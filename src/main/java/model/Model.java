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
    private OwnedPuckemon trainerPuckemon = new OwnedPuckemon(1, 3);
    private List<OwnedPuckemon> playerList = new ArrayList<>();
    private List<OwnedPuckemon> trainerList = new ArrayList<>();


    private Player player;
    private PuckeTrainer trainer;
    private Combat combat;

    public Model() {
        playerList.add(playerPuckemon);
        trainerList.add(trainerPuckemon);

        player = new Player(playerList, 10);
        trainer = new PuckeTrainer("Bertil the great", trainerList);

        combat = new Combat(player, trainer);
    }

    public OwnedPuckemon getPlayerPuckemon() {
        return playerPuckemon;
    }

    public OwnedPuckemon getTrainerPuckemon() {
        return trainerPuckemon;
    }
}