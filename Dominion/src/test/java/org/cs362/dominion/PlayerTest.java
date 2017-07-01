package org.cs362.dominion;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by kia on 2/13/17.
 */
public class PlayerTest {

    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    private List<Card> cards;
    private GameState state;

    @Before
    public void initGame() throws CloneNotSupportedException {

        cards = new ArrayList<Card>(Card.createCards());
        state = new GameState(cards);

        player1 = new Player(state, "player-1");
        state.addPlayer(player1);

        player2 = new Player(state, "player-2");
        state.addPlayer(player2);

        Random random = new Random();
        int playersNum = random.nextInt(4);

        cards = new ArrayList<Card>(Card.createCards());
        state = new GameState(cards);

        player1 = new Player(state, "player-1");
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

        System.out.println("Players Playing" + state.players.size());

        state.initializeGame();
    }

    @Test
    public void drawCard() {

        System.out.println("\n --------Player 1 Status--------");
        System.out.println("|                               ");
        System.out.println("|Actual: " + player1.hand.size() + " Expected: " + 5);
        System.out.println("|" + player1.hand);
        System.out.println(" ------------------------------");

        assertEquals(player1.hand.size(), 5);

        player1.drawCard();

        System.out.println("\n --------Player 1 Updated Status--------");
        System.out.println("|                               ");
        System.out.println("|Actual: " + player1.hand.size() + " Expected: " + 6);
        System.out.println("|" + player1.hand);
        System.out.println(" ------------------------------");

        assertEquals(player1.hand.size(), 6);

        System.out.println("\nTEST SUCCESSFULLY COMPLETED\n");
    }

    @Test
    public void discard() throws Exception {

        System.out.println("\n --------Player 1 Status--------");
        System.out.println("|                               ");
        System.out.println("|Actual Hand: " + player1.hand.size() + " Expected: " + 5);
        System.out.println("|" + player1.hand);
        System.out.println("|Actual Dicard: " + player1.discard.size() + " Expected: " + 0);
        System.out.println("|" + player1.discard);
        System.out.println(" ------------------------------");

        assertEquals(player1.hand.size(), 5);
        assertEquals(player1.discard.size(), 0);

        player1.discard(Card.getCard(cards, Card.CardName.Cooper));

        System.out.println("\n --------Player 1 Status--------");
        System.out.println("|                               ");
        System.out.println("|Actual Hand: " + player1.hand.size() + " Expected: " + 4);
        System.out.println("|" + player1.hand);
        System.out.println("|Actual Dicard: " + player1.discard.size() + " Expected: " + 1);
        System.out.println("|" + player1.discard);
        System.out.println(" ------------------------------");

        assertEquals(player1.hand.size(), 4);
        assertEquals(player1.discard.size(), 1);

        System.out.println("\nTEST SUCCESSFULLY COMPLETED\n");
    }

    @Test
    public void gain() throws Exception {

        System.out.println("\n -----PLAYER 1 DISCARD STATUS------");
        System.out.println("|                              ");
        System.out.println("|" + player1.discard);
        System.out.println(" -----------------------------");

        assertEquals(player1.discard.size(), 0);

        player1.gain(Card.getCard(cards, Card.CardName.Ambassador));

        System.out.println("\n -----PLAYER 1 UPDATED STATUS------");
        System.out.println("|Actual: " + player1.discard.size() + " Expected: " + 1);
        System.out.println("|" + player1.discard);
        System.out.println(" -----------------------------");

        assertEquals(player1.discard.size(), 1);

        System.out.println("\nTEST SUCCESSFULLY COMPLETED\n");
    }

    @Test
    public void kingDomCard() {

        System.out.println("\n -----PLAYER 1 STATUS------");
        System.out.println("|Player Hand: " + player1.hand);
        System.out.println("|                              ");
        System.out.println(" -----------------------------");

        assertEquals(player1.hand.size(), 5);

        player1.hand.add(Card.getCard(cards, Card.CardName.Smithy));
        player1.playKingdomCard();

        System.out.println("\n -----PLAYER 1 UPDATE STATUS------");
        System.out.println("|                              ");
        System.out.println("|Actual: " + player1.hand.size() + " Expected: " + 9);
        System.out.println("|" + player1.hand);
        System.out.println(" -----------------------------");

        assertEquals(player1.hand.size(), 9);

        System.out.println("\nTEST SUCCESSFULLY COMPLETED\n");
    }

    @Test
    public void buyCards() {

        player1.coins = 10;
        System.out.println("\n -----PLAYER 1 STATUS------");
        System.out.println("|                              ");
        System.out.println("|Player Hand: " + player1.hand);
        System.out.println("|Player Coins: " + player1.coins);
        System.out.println(" -----------------------------");

        assertEquals(player1.hand.size(), 5);

        //Get Card
        Card bought = player1.hand.get(player1.hand.size() - 1);

        player1.buyCard(state.gameBoard);

        System.out.println("\n -----PLAYER 1 UPDATE STATUS------");
        System.out.println("|                              ");
        System.out.println("|Actual: " + player1.hand.size() + " Expected: " + 6);
        System.out.println("|Actual Coins: " + player1.coins + " Expected: " + (player1.coins - bought.getTreasureValue()));
        System.out.println("|Player UPDATED Hand: " + player1.hand);
        System.out.println(" -----------------------------");


        assertEquals(player1.hand.size(), 6);
        assertEquals(player1.coins, (player1.coins - bought.getTreasureValue()));

        System.out.println("\nTEST SUCCESSFULLY COMPLETED\n");
    }

    @Test
    public void EndTurns() {

        System.out.println("\n -----BEFORE ENDING PLAYER TURN------");
        System.out.println("|                                       ");
        System.out.println("|Player Hand: " + player1.hand.size());
        System.out.println("|Player Discard: " + player1.hand.size());
        System.out.println("|Player Deck: " + player1.hand.size());
        System.out.println(" -------------------------------------");

        assertEquals(player1.hand.size(), 5);
        assertEquals(player1.discard.size(), 0);
        assertEquals(player1.deck.size(), 5);

        player1.endTurn();

        System.out.println("\n -----AFTER ENDING PLAYER TURN------");
        System.out.println("|                                       ");
        System.out.println("|Player Hand: " + player1.hand.size());
        System.out.println("|Player Discard: " + player1.hand.size());
        System.out.println("|Player Deck: " + player1.hand.size());
        System.out.println(" -------------------------------------");

        assertEquals(player1.hand.size(), 5);
        assertEquals(player1.discard.size(), 5);
        assertEquals(player1.deck.size(), 0);

        System.out.println("\nTEST SUCCESSFULLY COMPLETED\n");
    }
}
