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
    private final List<OwnedPuckemon> playerStartingTeam;

    public PartyBuilder(){
        createPuckemon = new CreatePuckemon();
        playerStartingTeam = new ArrayList<>();
        fillPlayerStartingTeam();
    }


    /**
     * Specify what the Player should start with.
     */
    private void fillPlayerStartingTeam(){
        playerStartingTeam.add(createPuckemon.createOwnedPuckemon(1,15));
        playerStartingTeam.add(createPuckemon.createOwnedPuckemon(2,5));
        playerStartingTeam.add(createPuckemon.createOwnedPuckemon(3,10));
        playerStartingTeam.add(createPuckemon.createOwnedPuckemon(4,15));
        playerStartingTeam.add(createPuckemon.createOwnedPuckemon(5,15));
    }

    /**
     * Creates a random team of Puckemon for the opponent.
     * @param level determines the lowest level.
     * @param size is how many puckemon the team should consist of.
     * @return List of VildPuckemon
     */
    private List<FixedPuckemon> randOpponentTeam(int size, int level){
        List<FixedPuckemon> opponentTeam = new ArrayList<>();
        for (int i = 0; i < size;i++){
            opponentTeam.add(createPuckemon.createFixedPuckemon(randomNumber(1,5),randomNumber(level,level+10)));
        }
        return opponentTeam;
    }

    private int randomNumber(int min, int max){
        return (int)Math.floor(Math.random()*(max-min+1)+min);
    }

    public List<OwnedPuckemon> getPlayerStartingTeam() {
        return playerStartingTeam;
    }

    public List<FixedPuckemon> getRandOpponentTeam(int partySize, int minLevel){
        return randOpponentTeam(partySize,minLevel);
    }
}
