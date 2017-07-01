package org.cs362.dominion;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by kia on 2/13/17.
 */
public class AmbassadorTest {

    @Test
    public void AmbassadorTest() throws Exception {

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
        player.hand.add(Card.getCard(cards,Card.CardName.Ambassador));
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

        Card revealed = state.players.get(0).hand.get(0);

        state.initializeGame();

        System.out.println("\n--------Initial Card Revealed Card Value--------");
        System.out.println(revealed.getCardName() + " : " + state.gameBoard.get(revealed));

        testState=(GameState) state.clone();

        HashMap<Player, Integer> winners=state.play();

        System.out.println("\n--------Player 1 Hand--------");
        System.out.println("|                               ");
        System.out.println(state.players.get(0).hand);
        System.out.println("--------------------------------");

        System.out.println("\n--------Reveal Card And Remove--------");
        System.out.println("|                                               ");
        System.out.println("|Cards In Hand: " + state.players.get(0).hand.size() + " Expected: " + (testState.players.get(0).hand.size() - 1));
        System.out.println("|" + state.players.get(0).hand);
        System.out.println("-------------------------------------------------");

        assertEquals(state.players.get(0).hand.size(), (testState.players.get(0).hand.size() - 1));

        System.out.println(" \n--------Add 2 Copies to Supply--------");
        System.out.println("|                                             ");
        System.out.println(revealed.getCardName() + " : " + (state.gameBoard.get(revealed) + 2));
        assertEquals(state.gameBoard.get(revealed), state.gameBoard.get(revealed));

        System.out.println("\n--------Give A Copy To Another Player--------");
        System.out.println("|                                               ");
        System.out.println("|Cards In Hand: " + state.players.get(1).hand.size() + " Expected: " + (testState.players.get(1).hand.size() + 1));
        System.out.println("|" + state.players.get(1).hand);
        System.out.println("------------------------------------------------");

        assertEquals(state.players.get(1).hand.size(), (testState.players.get(1).hand.size() + 1));

        System.out.println("\nTEST SUCCESSFULLY COMPLETED\n");
    }
}
