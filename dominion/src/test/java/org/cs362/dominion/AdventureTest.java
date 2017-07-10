package org.cs362.dominion;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by kia on 2/13/17.
 */
public class AdventureTest {

    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    private List<Card> cards;
    private GameState state, testState;

    @Before
    public void initGame() throws CloneNotSupportedException {

        Randomness.reset(10);

        Random r = new Random();
        int playersNum = r.nextInt( 4 - 2) + 4;

        cards = new ArrayList<Card>(Card.createCards());
        state = new GameState(cards);

        player1 = new Player(state, "player-1");
        player1.hand.add(Card.getCard(cards, Card.CardName.Adventurer));
        state.addPlayer(player1);

        player2 = new Player(state, "player-2");
        state.addPlayer(player2);

        if (playersNum == 3) {

            player3 = new Player(state, "player-3");
            state.addPlayer(player3);

        } else if (playersNum == 4) {

            player3 = new Player(state, "player-3");
            state.addPlayer(player3);

            player4 = new Player(state, "player-4");
            state.addPlayer(player4);
        }

        System.out.println("Number Of Players is " + state.players.size());

        state.initializeGame();

        testState=(GameState) state.clone();
    }

    @Test
    public void adventure() throws Exception {

        int treasureReavead = 2;

        System.out.println(" --------Player 1 Status--------");
        System.out.println("|                               ");
        System.out.println("|Cards In Hand: " + (state.players.get(0).hand.size()));
        System.out.println("|Cards In Discard: " + state.players.get(0).discard.size());
        System.out.println("|Coins: " + state.players.get(0).coins);
        System.out.println(" ------------------------------");

        testState = (GameState) state.clone();

        HashMap<Player, Integer> winners = state.play();

        int handSize = testState.players.get(0).hand.size();

        System.out.println("\n-------Player 1 HAND-------\n");
        System.out.println("Actual: " + state.players.get(0).hand.size() + " Expected : " + (handSize -= handSize - treasureReavead));

        System.out.println("\n-------Player 1 UPDATED HAND-------\n");
        System.out.println("Actual: " + state.players.get(0).hand);

        assertEquals(state.players.get(0).hand.size(), (handSize -= handSize - treasureReavead));

        System.out.println("\nTEST SUCCESSFULLY COMPLETED\n");
    }
}