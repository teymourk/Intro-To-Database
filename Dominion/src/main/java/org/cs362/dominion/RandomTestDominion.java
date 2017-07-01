package org.cs362.dominion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class RandomTestDominion {

    public  static void main(String args[]){

        List<Card> cards;
        GameState state;

        cards = new ArrayList<Card>(Card.createCards());
        state = new GameState(cards);

        Random r = new Random();
        int playersNum = r.nextInt( 4 - 2) + 4;

        Player player = new Player(state, "player-1");
        state.addPlayer(player);

        player = new Player(state, "player-2");
        state.addPlayer(player);

        if (playersNum == 3) {

            player = new Player(state, "player-3");
            state.addPlayer(player);

        } else if (playersNum == 4) {

            player = new Player(state, "player-3");
            state.addPlayer(player);

            player = new Player(state, "player-4");
            state.addPlayer(player);
        }

        System.out.println("Number Of Players is " + state.players.size());

        if(state.players.size() >= 2 && state.players.size() <= 4)
        {
            System.out.println("Correct # Players");

        } else {
            System.out.println("Incorret # of Players");
        }

        state.initializeGame();

        System.out.println("\nInitialization of Dominion Board:\n\n" + state.toString());


        HashMap<Player, Integer> winners = state.play();
        System.out.println ("Finished game.\n\n");

        for(Player p: winners.keySet()){
            System.out.println ("Player name: " + p.player_username + " , Score: " + winners.get(p) );
        }

        player.printStateGame();

        System.exit(0);
    }
}
