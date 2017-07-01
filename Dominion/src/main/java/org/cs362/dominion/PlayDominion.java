package org.cs362.dominion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class PlayDominion {

	   public  static void main(String args[]){

		    List<Card> cards;
		    GameState state;
			Randomness.reset(10);

			//the cards  are achieved by each element/constant in the enum class
			cards = new ArrayList<Card>(Card.createCards());
			state = new GameState(cards);

		   Player player = new Player(state, "Player-1");
		   state.addPlayer(player);

		   player = new Player(state, "Player-2");
		   state.addPlayer(player);

		   	//state.initializeGame();

		   	System.out.println("Initialization DominionBoard:\n " + state.toString());

		   	HashMap<Player, Integer> winners=state.play();
		   	System.out.println ("Finished game.\n");

		   	for(Player p: winners.keySet()){
		   		System.out.println ("Player name: "+winners.get(p) + " , Score: "+ winners.get(p) );
			}

			System.exit(0);
	   }
}
