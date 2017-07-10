package org.cs362.dominion;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by kia on 2/11/17.
 */
public class GameStateTest {
    @Test
    public void addPlayer() throws Exception {

        GameState state;

        state = new GameState(null);

        Randomness.reset(4);

        Random random = new Random();
        int playersNum = random.nextInt(5);

        Player player = new Player(state, "player-1");
        state.addPlayer(player);

        player = new Player(state, "player-2");
        state.addPlayer(player);

        System.out.println("Number Of Players is " + state.players.size());

        System.out.println("\n-------Player-------");
        System.out.println("Actual: " + state.players.size() + " Expected : " + 2);

        if (playersNum == 3) {

            player = new Player(state, "player-3");
            state.addPlayer(player);
            assertEquals(state.players.size(), 3);

        } else if (playersNum == 4) {

            player = new Player(state, "player-3");
            state.addPlayer(player);

            player = new Player(state, "player-4");
            state.addPlayer(player);
            assertEquals(state.players.size(), 4);
        } else if (playersNum == 2) {

            assertEquals(state.players.size(), 2);
        }


        System.out.println("\nTEST SUCCESSFULLY COMPLETED\n");
    }
}
