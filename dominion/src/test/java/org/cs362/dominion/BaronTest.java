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
public class BaronTest {

    @Test
    public void BaronTest() throws Exception {

        List<Card> cards;
        GameState state,testState;

        cards = new ArrayList<Card>(Card.createCards());
        state = new GameState(cards);
        Randomness.reset(10);


        Random r = new Random();
        int playersNum = r.nextInt( 4 - 2) + 4;

        cards = new ArrayList<Card>(Card.createCards());
        state = new GameState(cards);

        Player player = new Player(state, "player-1");
        player.hand.add(Card.getCard(cards,Card.CardName.Baron));
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
        System.out.println("|                              |");
        System.out.println("|Cards In Hand: " + (state.players.get(0).hand.size()));
        System.out.println("|Cards In Discard: " + state.players.get(0).discard.size());
        System.out.println("|Coins: " + state.players.get(0).coins);
        System.out.println("--------------------------------");

        testState=(GameState) state.clone();

        HashMap<Player, Integer> winners=state.play();

        Card bought = state.players.get(0).hand.get(state.players.get(0).hand.size() - 1);

        if(state.players.get(0).decision) {

            System.out.println("\n--------Player 1 Chooses To Discard Estate +1 Buy--------");
            System.out.println("|                                                          ");
            System.out.println("|Cards In Hand: " + state.players.get(0).hand.size() + " Expected: " + (testState.players.get(0).hand.size()));
            System.out.println("|Cards In Discard: " + state.players.get(0).discard.size() + " Expected: " + (testState.players.get(0).discard.size() + 1));
            System.out.println("|Coins: " + state.players.get(0).coins + " Expected: " + (testState.players.get(0).coins - bought.getCost() + bought.getTreasureValue() + 4));
            System.out.println("---------------------------------------------------------");

            assertEquals(state.players.get(0).hand.size(), testState.players.get(0).hand.size());
            assertEquals(state.players.get(0).discard.size(), testState.players.get(0).discard.size() + 1);

        } else {

            System.out.println("\n--------Player 1 Gains Estate--------\n");
            System.out.println("|                                        ");
            System.out.println("|Cards In Hand: " + state.players.get(0).hand.size() + " Expected: " + (testState.players.get(0).hand.size() + 2));
            System.out.println("--------------------------------------");

            assertEquals(state.players.get(0).hand.size(), testState.players.get(0).hand.size() + 2);

            System.out.println("\nTEST SUCCESSFULLY COMPLETED\n");
        }
    }
}
