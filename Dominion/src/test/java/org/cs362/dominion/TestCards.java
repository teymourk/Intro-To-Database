package org.cs362.dominion;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kia on 2/13/17.
 */
public class TestCards {

    private Player player1;
    private Player player2;
    private List<Card> cards;
    private GameState state, testState;

    @Before
    public void initGame() throws CloneNotSupportedException {

        cards = new ArrayList<Card>(Card.createCards());
        state = new GameState(cards);

        player1 = new Player(state, "player-1");
        state.addPlayer(player1);

        player2 = new Player(state, "player-2");
        state.addPlayer(player2);

        state.initializeGame();

        testState=(GameState) state.clone();
    }

    //VALUES FOR EACH CARD TEST
    @Test
    public void gold() {

        System.out.print("Playing Gold");

        Card gold = Card.getCard(cards, Card.CardName.Gold);
        player1.hand.clear();

        //Treasure
        assertEquals(gold.getTreasureValue(), 3);
        //Score
        assertEquals(gold.score(), 0);
        //Cost
        assertEquals(gold.getCost(), 6);

        //Test Card
        assertEquals(player1.coins, 0);

        player1.hand.add(gold);

        player1.playTtreasureCard();

        assertEquals(player1.coins, 3);

        System.out.println("\nTEST SUCCESSFULLY COMPLETED\n");
    }

    @Test
    public void silver() {

        System.out.print("Playing Silver");

        Card silver = Card.getCard(cards, Card.CardName.Silver);
        player1.hand.clear();

        //Treasure
        assertEquals(silver.getTreasureValue(), 2);
        //Score
        assertEquals(silver.score(), 0);
        //Cost
        assertEquals(silver.getCost(), 3);

        //Test Card
        assertEquals(player1.coins, 0);

        player1.hand.add(silver);

        player1.playTtreasureCard();

        assertEquals(player1.coins, 2);

        System.out.println("\nTEST SUCCESSFULLY COMPLETED\n");
    }

    @Test
    public void copper() {

        System.out.print("Playing Copper");

        Card copper = Card.getCard(cards, Card.CardName.Cooper);
        player1.hand.clear();

        //Treasure
        assertEquals(copper.getTreasureValue(), 1);
        //Score
        assertEquals(copper.score(), 0);
        //Cost
        assertEquals(copper.getCost(), 0);

        //Test Card
        assertEquals(player1.coins, 0);

        player1.hand.add(copper);

        player1.playTtreasureCard();

        assertEquals(player1.coins, 1);

        System.out.println("\nTEST SUCCESSFULLY COMPLETED\n");
    }
}

