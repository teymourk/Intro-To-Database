package org.cs362.dominion;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by kia on 2/12/17.
 */
public class StewardTest {

    @Test
    public void stewardTest() throws Exception {

        List<Card> cards;
        GameState state,testState;
        Randomness.reset(10);

        int newCards = 2;
        int extraCoins = 2;

        cards = new ArrayList<Card>(Card.createCards());
        state = new GameState(cards);


        Card steward = Card.getCard(cards,Card.CardName.Steward);

        Random r = new Random();
        int playersNum = r.nextInt( 4 - 2) + 4;

        cards = new ArrayList<Card>(Card.createCards());
        state = new GameState(cards);

        Player player = new Player(state, "player-1");
        player.hand.add(steward);
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

        state.initializeGame();

        System.out.println("\n--------Player 1 Status--------");
        System.out.println("|                              			 ");
        System.out.println("|hand Count:" + state.players.get(0).hand.size());
        System.out.println("|" + state.players.get(0).hand);
        System.out.println("|Deck Count = " + state.players.get(0).deck.size());
        System.out.println("|" + state.players.get(0).deck);
        System.out.println(" -----------------------------------------");

        testState=(GameState) state.clone();

        HashMap<Player, Integer> winners=state.play();

        if(state.players.get(0).decision) {

            System.out.println("\n -------Player Decided To Add 2 Cards-------" );
            System.out.println("|                              			 ");
            System.out.println("|Hand Count = " + state.players.get(0).hand.size() + ", expected = "+ ( testState.players.get(0).hand.size() + newCards ));
            System.out.println("|" + state.players.get(0).hand);
            System.out.println("|Discard count = " + state.players.get(0).deck.size() + ", expected = "+ (testState.players.get(0).deck.size() - newCards));
            System.out.println("|" + state.players.get(0).deck);
            System.out.println(" --------------------------------------------");

            assertEquals(state.players.get(0).hand.size(), (testState.players.get(0).hand.size() + newCards));
            assertEquals(state.players.get(0).deck.size(),( testState.players.get(0).deck.size() - newCards));

        } else {

            System.out.println("\n --------Player Decided To Get 2 Coins--------");
            System.out.println("|                              			 ");
            System.out.println("|Coins = " + state.players.get(0).coins + ", expected = " + (testState.players.get(0).coins + extraCoins));
            System.out.println(" ----------------------------------------------");

            assertEquals(state.players.get(0).coins,( testState.players.get(0).coins + extraCoins));

            System.out.println("\nTEST SUCCESSFULLY COMPLETED\n");
        }
    }
}
