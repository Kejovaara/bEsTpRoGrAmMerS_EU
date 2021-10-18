package model;

import model.entities.CreatePuckemon;
import model.entities.OwnedPuckemon;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that creates the starting team of the Player and creates opponents with specific or random teams.
 */

public class GameBuilder {

    private final CreatePuckemon createPuckemon;
    private List<OwnedPuckemon> playerStartingTeam;

    public GameBuilder(){
        createPuckemon = new CreatePuckemon();
        fillPlayerStartingTeam();
    }


    /**
     * Specify what the Player should start with.
     */
    private void fillPlayerStartingTeam(){
        playerStartingTeam.add(createPuckemon.createOwnedPuckemon(1,5));
        playerStartingTeam.add(createPuckemon.createOwnedPuckemon(2,5));
        playerStartingTeam.add(createPuckemon.createOwnedPuckemon(3,10));
        playerStartingTeam.add(createPuckemon.createOwnedPuckemon(4,10));
        playerStartingTeam.add(createPuckemon.createOwnedPuckemon(5,15));
    }

    private List<OwnedPuckemon> randOpponentTeam(int size, int level){
        List<OwnedPuckemon> opponentTeam = new ArrayList<>();
        for (int i = 0; i < size;i++){
            opponentTeam.add(createPuckemon.createOwnedPuckemon(randomNumber(1,5),randomNumber(level,level+10)));
        }
        return opponentTeam;
    }

    private int randomNumber(int min, int max){
        return (int)Math.floor(Math.random()*(max-min+1)+min);
    }

    public List<OwnedPuckemon> getPlayerStartingTeam() {
        return playerStartingTeam;
    }

    public List<OwnedPuckemon> getRandOpponentTeam(int partySize, int minLevel){
        return randOpponentTeam(partySize,minLevel);
    }
}
