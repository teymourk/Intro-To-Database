package org.cs362.dominion;

import org.cs362.dominion.Card.Type;

import java.util.*;

public class Player implements Cloneable{

    List<Card> hand = new ArrayList<Card>();
	List<Card> discard = new ArrayList<Card>();
	List<Card> playedCards = new ArrayList<Card>();
    List<Card> cardsToBuy = new ArrayList<Card>();

    LinkedList<Card> deck = new LinkedList<Card>();

	String player_username;

	int numActions;
	int numBuys;
	int coins;
	int score;
	boolean decision;

    final GameState gameState;

	public Player(GameState gameState,String player_username) {
		this.player_username = player_username;
		this.gameState=gameState;
	}

	final Card drawCard() {
		if (deck.isEmpty()) {// Deck is empty
			// Step 1 Shuffle the discard pile back into a deck
			System.out.println("reshuffle the deck of the player "
					+ player_username + " to draw FIVE cards");
			while (discard.size() > 0) {
				int ndx = (int) Randomness.nextRandomInt(discard.size());
				// Move discard to deck
				deck.add(discard.remove(ndx));
			}
		}

		Card toDraw = deck.poll();
		hand.add(toDraw);// Add card to hand and hand count automatically will
							// be incremented since we use List
		System.out.println("draw " + toDraw);

		Collections.sort(hand);
		return toDraw;
	}
	
	   final void initializePlayerTurn() {

		      numActions = 1;
		      coins = 0;
		      numBuys = 0;

		      for (int i = 0; i < 5; i++) {
		    	  drawCard();
		      }
		   }

	 //card goes in discard,
	   final boolean gain(Card card) {
		      discard.add(card);
		      System.out.println(this.player_username + " gains " + card);
		      return true;
		   }		   
		 //Discard hand
	   public void discard(Card card) {
		     hand.remove(card);
		      discard.add(card);
		      System.out.println("Player:  " + player_username+" discards " + card);
		   }
	   public void playKingdomCard() {
		      if (numActions > 0) {
		         List<Card> actionCards = Card.filter(hand, Type.ACTION);
		   
		         if (actionCards.size() == 0)
		            return;
		         
		         Card c = (Card)actionCards.get(0);

		         if (c == null)
			           return;
		         System.out.println("Player.actionPhase Card:" + c.toString());

			      playedCards.add(c);
			      numActions -= 1;

			      c.play(this, gameState);
		      }
		   }

	   final int scoreFor() { 

		   //score from hand
		      for (Card c : hand)
		    	  score += c.score();
		      //score from discard
		      for (Card c : discard)
		    	  score += c.score();
		      //score from deck
		      for (Card c : deck)
		    	  score += c.score();

		      return score;
	   }

	   
	   public void playTtreasureCard() {

			System.out.println("Playing Treasure Card");
			List<Card> treasureList = Card.filter(hand, Card.Type.TREASURE);

			if(treasureList != null) {

				for(Card c: treasureList) {

					playedCards.add(c);
					hand.remove(c);
					coins += c.getTreasureValue();
				}
			}

		   System.out.println(player_username + " COINS: " + coins);
	   }
	   public void buyCard(HashMap<Card, Integer> gameBoard) {

		   if(cardsToBuy != null) {
		        cardsToBuy.clear();
           }

           for (Card card : gameBoard.keySet()) {

               if (card.getCost() <= this.coins) {
                   cardsToBuy.add(card);
               }
           }

		   Random random = new Random();

		   int answer = random.nextInt(cardsToBuy.size());
		   Card card = cardsToBuy.get(answer);

		   this.hand.add(card);

		   coins -= card.getCost();
		   coins += card.getTreasureValue();

		   numBuys += 1;

		   System.out.println(this.player_username + " Bough" + card);

		   gameBoard.put(card, gameBoard.get(card) - 1);
	   }
	   final void endTurn() {

		   if(this.hand != null) {

		   		for(int i=0; i < this.hand.size(); i++){

		   			this.discard.add(this.hand.get(i));
				}

			   this.hand.clear();
               System.out.println("Cards From Hand Were Discarded");
		   }

		   if(this.deck != null) {

			   for(int i=0; i < this.deck.size(); i++) {

				   this.hand.add(this.deck.get(i));
			   }

			   this.deck.clear();
		   }

		   if(hand.isEmpty()) {

			   for(int i=0; i < 5; i++) {

			       System.out.println(this.player_username + " Draws 5 random Cards From Discard");

				   drawCard();
			   }

		   } else if(hand.size() < 5) {

		        int needCard = 5- hand.size();

		   		for(int i = 0; i < needCard; i++) {

                    System.out.print(this.player_username + "Draws " + needCard + " random Cards From Discard");

					drawCard();
				}
		   }

		   System.out.println(this.toString());
	   }

	   public void printStateGame(){
		   System.out.println(" --- " + this.player_username + " --- ");
		   System.out.println(" --- --------------------------- --- ");
		   System.out.println(this.gameState.toString());
		   System.out.println(" --- --------------------------- --- ");
	   }
	   @Override
		public String toString() {
		     StringBuilder sb = new StringBuilder();
		     
       	 sb.append(" --- " + this.player_username + " --- ");
       	 sb.append(" --- --------------------------- --- ");

       	 sb.append("\nHand: " + this.hand);
       	 sb.append("\nDiscard: " + this.discard);
       	 sb.append("\nDeck: " + this.deck);
       	 sb.append("\nPlayed Cards: " + this.playedCards);
       	 sb.append("\nnumActions: " + this.numActions);
       	 sb.append("\ncoins: " + this.coins);
       	 sb.append("\nnumBuys: " + this.numBuys);
       	 sb.append("\n");     

		     return sb.toString();
		}
	   
	    protected Player clone() throws CloneNotSupportedException {
	    	Player clonePlayer=(Player) super.clone();
	    	clonePlayer.hand = new ArrayList<Card>(hand);// int hand[MAX_PLAYERS][MAX_HAND];
			clonePlayer.deck = new LinkedList<Card>(deck);// int
															// deck[MAX_PLAYERS][MAX_DECK];
			clonePlayer.discard = new ArrayList<Card>(discard); // int
														// discard[MAX_PLAYERS][MAX_DECK];
			clonePlayer.playedCards = new ArrayList<Card>(playedCards);
			return clonePlayer;
	    } 	   
}
