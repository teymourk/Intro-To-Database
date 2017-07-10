package org.cs362.dominion;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public final class Card implements Comparable<Card>, Cloneable{

	public static enum Type {
		ACTION, TREASURE, VICTORY;
	}

	public static enum CardName {
		/** The Treasure cards */
		Gold, Silver, Cooper, 
		/** The Victory cards */
		Province,Duchy,Estate,Curse,
		/** The Kingdom cards 10 Alphabe */
		Adventurer, Ambassador, Baron, Council_Room, Cutpurse, Embargo, Feast, Remodel, Great_Hall, Mine,Smithy, Steward, Village;
	}
	/**
	 * @param enum CardName the name of the card (GOLD, SILVER, COPPER, 
	 * @param enum Type the type of the card (Victory, Treasure, or Kingdom
	 * @param int cost the cost of the card
	 * @param int score the score of the card, when the game is finished
	 * @param treasureValue  the treasure value of the treasure cards (cooper, silver, and gold cards)
	 */
	private final Type realType;
	private final CardName cardName;
	private final int cost, score, treasureValue;

	private Card(CardName cardName, Type type, int cost, int score, int treasureValue) {
		this.cost = cost;
		this.score = score;
		this.treasureValue = treasureValue;
		this.realType = type;
		this.cardName = cardName;
	}

	/**
	 * Returns the type
	 */
	public Type getType() {
		return realType;
	}

	/**
	 * Returns the cardName
	 */
	public CardName getCardName() {
		return cardName;
	}
	/**
	 * Returns the cost
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * Returns the treasureValue
	 */
	public int getTreasureValue() {
		return treasureValue; 
	}
	public int score() { 
			return score; 
		}
	
	public static List<Card> createCards() {
		List<Card> ret = new LinkedList<Card>();

		/** The Treasure cards  */
		Card o = new Card(CardName.Gold, Type.TREASURE, 6, 0, 3);
		ret.add(o);
		o = new Card(CardName.Silver, Type.TREASURE, 3, 0, 2);
		ret.add(o);
		o = new Card(CardName.Cooper, Type.TREASURE, 0, 0, 1);
		ret.add(o);

		/** The Victory cards  */ 
		o = new Card(CardName.Province,Type.VICTORY,8, 6, 0);
		ret.add(o);
		o = new Card(CardName.Duchy,Type.VICTORY,5, 1, 0);
		ret.add(o);		
		o = new Card(CardName.Estate,Type.VICTORY,2, 3, 0);
		ret.add(o);		
		o = new Card(CardName.Curse,Type.VICTORY,0, -1, 0);
		ret.add(o);				
		/** The Kingdom cards , it should more than 10 cards*/ 	

		/** 10 With Alphabet Order */
		o = new Card(CardName.Adventurer,Type.ACTION,6,0,0);
		ret.add(o);
		o = new Card(CardName.Ambassador,Type.ACTION,3,0,0);
		ret.add(o);	
		o = new Card(CardName.Baron,Type.ACTION,4,0,0);
		ret.add(o);
		o = new Card(CardName.Council_Room,Type.ACTION,5,0,0);
		ret.add(o);
		o = new Card(CardName.Cutpurse,Type.ACTION,4,0,0);
		ret.add(o);
		o = new Card(CardName.Embargo,Type.ACTION,2,0,0);
		ret.add(o);
		o = new Card(CardName.Feast,Type.ACTION,4,0,0);
		ret.add(o);
		o = new Card(CardName.Remodel,Type.VICTORY,4,0,0);
		ret.add(o);
		o = new Card(CardName.Great_Hall,Type.ACTION,3,0,0);
		ret.add(o);
		o = new Card(CardName.Mine,Type.ACTION,5,0,0);
		ret.add(o);
		o = new Card(CardName.Smithy,Type.ACTION,4,0,0);
		ret.add(o);
		o = new Card(CardName.Village,Type.ACTION,3,0,0);
		ret.add(o);
		o = new Card(CardName.Steward,Type.ACTION,3,0,0);
		ret.add(o);

		return ret;
	}
	
	public void play(Player player, GameState state) {

		switch(this.cardName) {

			case Adventurer:

			int treasureCount = 0;

			List<Card> newTreasure = new ArrayList<Card>();
			List<Card> newDiscard = new ArrayList<Card>();

			while (treasureCount < 2) {

				Random random = new Random();
				int i = random.nextInt(player.hand.size());

				if(player.hand.get(i).getType().equals(Type.TREASURE)) {

					treasureCount++;
					Card treasure = player.hand.get(i);

					for(int x=0; x < player.hand.size(); x++) {

						if(x != i) {

							player.hand.remove(x);
						}
					}
				}
			}

			return;
			case Ambassador:

				int i = (int) Randomness.nextRandomInt(player.hand.size());

				Card reavealed = player.hand.get(i);
				player.hand.remove(i);

				System.out.println(player.player_username + " revealed " + reavealed.getCardName());

				state.gameBoard.put(reavealed, state.gameBoard.get(reavealed) + 2);

				for(Player p: state.players) {

					if(!p.player_username.equals(player.player_username)) {

						p.hand.add(reavealed);
					}
				}

				return;

			case Baron:

				player.decision = true;
				player.buyCard(state.gameBoard);
				int oneEstate = 0;

				Card est = Card.getCard(state.cards, CardName.Estate);

				if(player.decision) {

					if(player.hand.contains(est)) {

						int indx = player.hand.indexOf(est);

						Card found = player.hand.get(indx);

						player.hand.remove(indx);
						player.discard.add(found);
						player.coins += 4;
						oneEstate++;
					}

				} else {

					player.hand.add(est);
					state.gameBoard.put(est, state.gameBoard.get(est) - 1);
				}

				return;

			case Council_Room:

				for (int x = 0; x < 4; x++) {
					player.drawCard();
				}

				player.buyCard(state.gameBoard);

				for(Player p: state.players) {

					if(!p.player_username.equals(player.player_username)) {

						p.drawCard();
					}
				}

				return;

			case Cutpurse:

				player.coins += 2;
				System.out.print(player.player_username + " Gained 2 Coins");

				Card Copper = Card.getCard(state.cards,Card.CardName.Cooper);

				int copper = 0;

				for(int n=0; n < state.players.get(0).hand.size(); n++) {

					while(copper < 1) {

						if(player.hand.get(n).equals(Copper)) {

							player.hand.remove(n);
							player.discard.add(Copper);
							copper++;
						}
					}
				}

				return;

			case Embargo:

				System.out.println(player.player_username + " Gained 2 Coins");
				player.coins += 2;

				return;

			case Feast:

				List<Card> cardUpToFive = new ArrayList<Card>();

				for(Card c : player.hand) {

					if(c.getCardName().equals(CardName.Feast)) {

						System.out.println("Player Trashed " + c.getCardName());
					}
				}

				for (Card card : state.gameBoard.keySet()) {

					if (card.getCost() <= 5) {

						cardUpToFive.add(card);
					}
				}

				int m = (int) Randomness.nextRandomInt(cardUpToFive.size());

				Card c = cardUpToFive.get(m);

				player.hand.add(c);
				System.out.println(player.player_username + " Picked " + c.getCardName());

				return;

			case Great_Hall:

				player.numActions += 1;
				player.drawCard();

				return;
		case Mine:

				List<Card> treasureCard  = new ArrayList<Card>();

				System.out.println("Mine Card Was Trashed");
				for (Card card : state.gameBoard.keySet()) {

					if (card.getType().equals(Type.TREASURE) && card.getCost() <= 3) {
						treasureCard.add(card);
					}
				}

				int r = (int) Randomness.nextRandomInt(treasureCard.size());

				Card t = treasureCard.get(r);

				player.hand.add(t);
				System.out.println(player.player_username + " Picked " + t.getCardName() + " that costs $" + t.treasureValue);

				int treasuresCount = 0;

				for(int k=0; k < player.hand.size(); k++) {

					while (treasuresCount < 1) {

						if(player.hand.get(k).getType().equals(Type.TREASURE)) {

							System.out.println("Treasure Card " + player.hand.get(k).getCardName() + " Was Trashed.");
							player.hand.remove(k);
							treasuresCount++;
						}
					}
				}

				return;

			case Remodel:

				List<Card> newCards = new ArrayList<Card>();

				Card firstCard = player.hand.get(0);

				int cardPrice = firstCard.getCost();

				for(Card v: state.gameBoard.keySet()) {

					if(v.getCost() >= cardPrice) {

						newCards.add(v);
					}
				}

				int rand = (int) Randomness.nextRandomInt(newCards.size());

				Card newC = newCards.get(rand);

				player.hand.add(newC);

				return;

			case Smithy:
				player.drawCard();
	         	player.drawCard();
	         	player.drawCard();
				return;

			case Steward:

				player.decision = false;

				if(player.decision) {
					player.drawCard();
					player.drawCard();

				} else{

					player.coins += 2;
				}

				break;
			case Village:

				player.numActions += 2;
				player.drawCard();

				System.out.println(player.player_username + " Gained 2 extra actions.");

			return;

		default: return;

		}
	}

	public static Card getCard(List<Card> cards,CardName cardName) {
		for(int i=0; i<cards.size();i++){
			if(cards.get(i).cardName.equals(cardName))
					return 	cards.get(i);
		}
		
		return null;

	}

	   public static List<Card> filter(Iterable<Card> cards, Type target) {
		      List<Card> out = new ArrayList<Card>();
		      for (Card c : cards)
		         if (c.getType() == target)
		            out.add(c);
		      return out;
		   }
	// this method will print out all the constant cards ENUM
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(this.getCardName());
//		sb.append("-" + this.getType() + " ");
//		sb.append("\t\t Cost: " + this.cost + " ");
//		sb.append("\t\t Score: " + this.score + " ");
//		sb.append("\tTreasure Value: " + this.treasureValue);

		return sb.toString();

	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(!super.equals(obj)) return false;
		if(!(obj instanceof Card)) return false;

		return cardName.equals(((Card) obj).cardName);
	}

	@Override
	public int compareTo(Card o) {
		// TODO Auto-generated method stub
		return cardName.compareTo(o.cardName);
	}
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
