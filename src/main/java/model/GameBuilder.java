package model;

import model.entities.CreatePuckemon;
import model.entities.OwnedPuckemon;
import model.entities.VildPuckemon;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that creates the starting team of the Player and creates the random or specific team of the Opponents.
 */

public class GameBuilder {

    private final CreatePuckemon createPuckemon;
    private final List<OwnedPuckemon> playerStartingTeam;

    public GameBuilder(){
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
        playerStartingTeam.add(createPuckemon.createOwnedPuckemon(4,17));
        playerStartingTeam.add(createPuckemon.createOwnedPuckemon(5,15));
    }

    /**
     * Creates a list with randomised Puckemon for the opponent
     */
    private List<VildPuckemon> randOpponentTeam(int size, int level){
        List<VildPuckemon> opponentTeam = new ArrayList<>();
        for (int i = 0; i < size;i++){
            opponentTeam.add(createPuckemon.createVildPuckemon(randomNumber(1,3),randomNumber(level,level+10)));
        }
        return opponentTeam;
    }

    private int randomNumber(int min, int max){
        return (int)Math.floor(Math.random()*(max-min+1)+min);
    }

    public List<OwnedPuckemon> getPlayerStartingTeam() {
        return playerStartingTeam;
    }

    public List<VildPuckemon> getRandOpponentTeam(int partySize, int minLevel){
        return randOpponentTeam(partySize,minLevel);
    }
}
