package model;

import model.entities.CreatePuckemon;
import model.entities.puckemon.OwnedPuckemon;
import model.entities.puckemon.FixedPuckemon;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that creates the starting team of the Player and creates the random or specific team of the Opponents.
 * @author Lukas Jigberg
 */
public class PartyBuilder {

    private final CreatePuckemon createPuckemon;

    public PartyBuilder(){
        createPuckemon = new CreatePuckemon();
    }

    /**
     * Specify what the Player should start with.
     */
    public List<OwnedPuckemon> getPlayerStartingTeam(){
        List<OwnedPuckemon> playerStartingTeam = new ArrayList<>();

        playerStartingTeam.add(createPuckemon.createOwnedPuckemon(1,15));
        playerStartingTeam.add(createPuckemon.createOwnedPuckemon(6,30));
        playerStartingTeam.add(createPuckemon.createOwnedPuckemon(3,10));
        playerStartingTeam.add(createPuckemon.createOwnedPuckemon(4,15));
        playerStartingTeam.add(createPuckemon.createOwnedPuckemon(5,15));
        return playerStartingTeam;
    }

    /**
     * Creates a random team of Puckemon for the opponent.
     * @param level determines the lowest level.
     * @param size is how many puckemon the team should consist of.
     * @return List of FixedPuckemon
     */
    public List<FixedPuckemon> getRandOpponentTeam(int size, int level){
        List<FixedPuckemon> opponentTeam = new ArrayList<>();
        for (int i = 0; i < size;i++){
            opponentTeam.add(createPuckemon.createFixedPuckemon(randomNumber(1,5),randomNumber(level,level+10)));
        }
        return opponentTeam;
    }

    private int randomNumber(int min, int max){
        return (int)Math.floor(Math.random()*(max-min+1)+min);
    }
}
