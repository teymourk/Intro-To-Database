package org.cs362.dominion;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by kia on 2/11/17.
 */
public class CouncilRoomTest {

    @Test
    public void CouncilRoomTest() throws Exception {

        List<Card> cards;
        GameState state,testState;
        Randomness.reset(10);

        int newCards = 4;

        cards = new ArrayList<Card>(Card.createCards());
        state = new GameState(cards);

        Random r = new Random();
        int playersNum = r.nextInt( 4 - 2) + 4;

        cards = new ArrayList<Card>(Card.createCards());
        state = new GameState(cards);

        Player player = new Player(state, "player-1");
        player.hand.add(Card.getCard(cards,Card.CardName.Council_Room));
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
        System.out.println("|                               ");
        System.out.println("|Cards In Hand: " + (state.players.get(0).hand.size()));
        System.out.println("|Cards In Discard: " + state.players.get(0).discard.size());
        System.out.println("|Coins: " + state.players.get(0).coins);
        System.out.println(" ------------------------------");

        testState=(GameState) state.clone();

        HashMap<Player, Integer> winners=state.play();

        System.out.println("\n--------Player 1 Updated Hand Status--------");
        System.out.println("|                                           |");
        System.out.println("|Cards In Hand: " + state.players.get(0).hand.size() + " Expected: " + (testState.players.get(0).hand.size() + newCards + 1));
        System.out.println("|" + state.players.get(0).hand);
        System.out.println(" --------------------------------------------");

        System.out.println("\n--------Player 2 Draws A Card--------");
        System.out.println("|                                      |");
        System.out.println("|Cards In Hand: " + state.players.get(1).hand.size() + " Expected: " + (testState.players.get(1).hand.size() + 1));
        System.out.println("|" + state.players.get(1).hand);
        System.out.println(" --------------------------------------");

        assertEquals(state.players.get(0).hand.size(), (testState.players.get(0).hand.size() + newCards + 1));
        assertEquals(state.players.get(1).hand.size(), (testState.players.get(1).hand.size() + 1));

        System.out.println("\nTEST SUCCESSFULLY COMPLETED\n");
    }
}
