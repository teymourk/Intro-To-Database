/*
 * This file was automatically generated by EvoSuite
 * Tue Feb 28 06:54:46 GMT 2017
 */

package org.cs362.dominion;

import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.Random;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.*;

import static org.evosuite.runtime.EvoAssertions.verifyException;
import static org.junit.Assert.fail;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = false, useJEE = true)
public class GameState_ESTest extends GameState_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      List<Card> list0 = Card.createCards();
      GameState gameState0 = new GameState(list0);
      GameState gameState1 = gameState0.clone();
      Player player0 = new Player(gameState0, "#d-l9Oi]K");
      gameState0.initializeGame();
      gameState1.addPlayer(player0);
      gameState0.initializeGame();
      player0.cardsToBuy = gameState0.cards;
      gameState1.toString();
      player0.drawCard();
      gameState1.addPlayer(player0);
      gameState1.gameBoard = gameState0.gameBoard;
      GameState gameState2 = gameState1.clone();
      HashMap<Player, Integer> hashMap0 = new HashMap<Player, Integer>();
      gameState0.initializeGame();
      GameState gameState3 = new GameState(player0.cardsToBuy);
      gameState1.players = gameState3.players;
      gameState1.getWinners();
      List<Card> list1 = gameState1.cards;
      gameState0.getWinners();
      gameState1.initializeGame();
      gameState1.initializeGame();
      Card.Type.values();
      gameState1.isGameOver();
      Random.setNextRandom(12);
      List<Player> list2 = gameState0.players;
      // Undeclared exception!
      gameState2.initializeGame();
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      Stack<Card> stack0 = new Stack<Card>();
      GameState gameState0 = new GameState((List<Card>) stack0);
      stack0.containsAll(gameState0.players);
      gameState0.initializeGame();
      stack0.clear();
      gameState0.initializeGame();
      gameState0.gameBoard = gameState0.gameBoard;
      Card.CardName card_CardName0 = Card.CardName.Estate;
      Card.getCard(stack0, card_CardName0);
      Integer integer0 = new Integer(3);
      gameState0.gameBoard.put((Card) null, integer0);
      gameState0.isGameOver();
      gameState0.initializeGame();
      Stack<Card> stack1 = new Stack<Card>();
      gameState0.cards = (List<Card>) stack1;
      Card.CardName.values();
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      Stack<Card> stack0 = new Stack<Card>();
      GameState gameState0 = new GameState((List<Card>) stack0);
      gameState0.getWinners();
      stack0.containsAll(gameState0.players);
      gameState0.initializeGame();
      stack0.clear();
      gameState0.play();
      gameState0.initializeGame();
      Randomness.reset(1L);
      stack0.listIterator();
      Player player0 = new Player(gameState0, "the number of players mus be between 2 and 4 ");
      gameState0.addPlayer(player0);
      gameState0.addPlayer(player0);
      gameState0.gameBoard = gameState0.gameBoard;
      gameState0.addPlayer(player0);
      Player player1 = player0.clone();
      player1.printStateGame();
      Integer integer0 = new Integer(754);
      gameState0.addPlayer(player1);
      gameState0.isGameOver();
      HashMap<Player, Integer> hashMap0 = new HashMap<Player, Integer>();
      gameState0.gameBoard.clear();
      gameState0.clone();
      // Undeclared exception!
      try { 
        gameState0.initializeGame();
        fail("Expecting exception: ArithmeticException");
      
      } catch(ArithmeticException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      Stack<Card> stack0 = new Stack<Card>();
      GameState gameState0 = new GameState((List<Card>) stack0);
      gameState0.getWinners();
      gameState0.initializeGame();
      gameState0.initializeGame();
      Player player0 = new Player(gameState0, "the number of players mus be between 2 and 4 ");
      player0.cardsToBuy = gameState0.cards;
      gameState0.addPlayer(player0);
      gameState0.clone();
      gameState0.gameBoard = gameState0.gameBoard;
      Card.CardName card_CardName0 = Card.CardName.Council_Room;
      Card.getCard(stack0, card_CardName0);
      Integer integer0 = Integer.valueOf((-162));
      gameState0.gameBoard.put((Card) null, integer0);
      // Undeclared exception!
      try { 
        player0.printStateGame();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      TreeSet<Card> treeSet0 = new TreeSet<Card>();
      Vector<Card> vector0 = new Vector<Card>((Collection<? extends Card>) treeSet0);
      GameState gameState0 = new GameState((List<Card>) vector0);
      Player player0 = new Player(gameState0, "\nDeck: ");
      player0.drawCard();
      gameState0.addPlayer(player0);
      // Undeclared exception!
      try { 
        gameState0.getWinners();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.cs362.dominion.Player", e);
      }
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      GameState gameState0 = new GameState((List<Card>) null);
      // Undeclared exception!
      try { 
        gameState0.play();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.cs362.dominion.Card", e);
      }
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      GameState gameState0 = new GameState((List<Card>) null);
      // Undeclared exception!
      try { 
        gameState0.isGameOver();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.cs362.dominion.Card", e);
      }
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      Stack<Card> stack0 = new Stack<Card>();
      GameState gameState0 = new GameState((List<Card>) stack0);
      gameState0.players = null;
      Player player0 = new Player(gameState0, "9u xqBAq>`D1WD");
      Player player1 = player0.clone();
      player1.drawCard();
      Card.CardName card_CardName0 = Card.CardName.Feast;
      Card.getCard(stack0, card_CardName0);
      player0.playKingdomCard();
      // Undeclared exception!
      try { 
        gameState0.addPlayer(player0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.cs362.dominion.GameState", e);
      }
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      GameState gameState0 = new GameState((List<Card>) null);
      // Undeclared exception!
      try { 
        gameState0.clone();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.cs362.dominion.GameState", e);
      }
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      ArrayList<Card> arrayList0 = new ArrayList<Card>();
      GameState gameState0 = new GameState((List<Card>) arrayList0);
      Player player0 = new Player(gameState0, (String) null);
      gameState0.addPlayer(player0);
      gameState0.players = null;
      // Undeclared exception!
      try { 
        gameState0.initializeGame();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.cs362.dominion.GameState", e);
      }
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      LinkedList<Card> linkedList0 = new LinkedList<Card>();
      GameState gameState0 = new GameState((List<Card>) linkedList0);
      gameState0.initializeGame();
      GameState gameState1 = gameState0.clone();
      gameState1.isGameOver();
      gameState0.toString();
      gameState0.play();
      gameState0.cards = (List<Card>) linkedList0;
      List<Card> list0 = linkedList0.subList(0, 0);
      linkedList0.clear();
      gameState1.getWinners();
      gameState0.play();
      gameState1.initializeGame();
      gameState1.play();
      linkedList0.parallelStream();
      gameState1.play();
      gameState1.initializeGame();
      gameState0.getWinners();
      HashMap<Card, Integer> hashMap0 = gameState1.gameBoard;
      gameState0.initializeGame();
      List<Player> list1 = gameState1.players;
      gameState0.toString();
      Card.Type.values();
      GameState gameState2 = new GameState(list0);
      // Undeclared exception!
      try { 
        gameState2.clone();
        fail("Expecting exception: ConcurrentModificationException");
      
      } catch(ConcurrentModificationException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("java.util.SubList", e);
      }
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      Stack<Card> stack0 = new Stack<Card>();
      GameState gameState0 = new GameState((List<Card>) stack0);
      Player player0 = new Player(gameState0, "\"S5s<DrWX");
      gameState0.addPlayer(player0);
      gameState0.getWinners();
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      Stack<Card> stack0 = new Stack<Card>();
      GameState gameState0 = new GameState((List<Card>) stack0);
      gameState0.isGameOver();
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      Stack<Card> stack0 = new Stack<Card>();
      GameState gameState0 = new GameState((List<Card>) stack0);
      gameState0.toString();
      gameState0.getWinners();
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      LinkedList<Card> linkedList0 = new LinkedList<Card>();
      GameState gameState0 = new GameState((List<Card>) linkedList0);
      HashMap<Card, Integer> hashMap0 = gameState0.gameBoard;
      gameState0.getWinners();
      hashMap0.entrySet();
      Card.CardName card_CardName0 = Card.CardName.Ambassador;
      Card.getCard(linkedList0, card_CardName0);
      Integer integer0 = new Integer((-1713));
      hashMap0.put((Card) null, integer0);
      gameState0.play();
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      Vector<Card> vector0 = new Vector<Card>();
      GameState gameState0 = new GameState((List<Card>) vector0);
      GameState gameState1 = gameState0.clone();
      HashMap<Player, Integer> hashMap0 = new HashMap<Player, Integer>();
      gameState1.getWinners();
      gameState1.gameBoard = gameState0.gameBoard;
      Card.CardName.values();
      gameState1.getWinners();
      Card.CardName card_CardName0 = Card.CardName.Steward;
      Card.getCard(gameState0.cards, card_CardName0);
      Integer integer0 = new Integer((-177));
      gameState1.gameBoard.put((Card) null, integer0);
      Card.Type.values();
      gameState1.gameBoard = gameState0.gameBoard;
      Player player0 = new Player((GameState) null, "");
      Player player1 = new Player(gameState1, "");
      gameState1.addPlayer(player1);
      gameState0.getWinners();
      Randomness.reset(2821L);
      gameState0.cards = (List<Card>) vector0;
      gameState1.play();
      gameState1.isGameOver();
      Randomness.reset((-420L));
      Randomness.random = null;
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      Card.Type.values();
      List<Card> list0 = Card.createCards();
      GameState gameState0 = new GameState(list0);
      HashMap<Card, Integer> hashMap0 = gameState0.gameBoard;
      hashMap0.entrySet();
      Card.CardName card_CardName0 = Card.CardName.Ambassador;
      Card card0 = Card.getCard(list0, card_CardName0);
      Integer integer0 = new Integer(1654);
      hashMap0.replace(card0, integer0);
      Integer integer1 = new Integer((-1713));
      hashMap0.put(card0, integer1);
      gameState0.play();
      gameState0.toString();
      gameState0.clone();
      gameState0.cards = list0;
      // Undeclared exception!
      try { 
        Card.Type.valueOf(" --- gameBoard --- \nCards on the table: \nCard Name \t\t NumberCards: \n\t Ambassador\t\t -1713\n");
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // No enum constant org.cs362.dominion.Card.Type. --- gameBoard --- 
         // Cards on the table: 
         // Card Name \t\t NumberCards: 
         // \t Ambassador\t\t -1713
         //
         verifyException("java.lang.Enum", e);
      }
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      Stack<Card> stack0 = new Stack<Card>();
      GameState gameState0 = new GameState((List<Card>) stack0);
      gameState0.getWinners();
      stack0.containsAll(gameState0.players);
      gameState0.initializeGame();
      gameState0.initializeGame();
      stack0.listIterator();
      Player player0 = new Player(gameState0, "the number of players mus be between 2 and 4 ");
      player0.drawCard();
      gameState0.addPlayer(player0);
      gameState0.gameBoard = gameState0.gameBoard;
      Player player1 = player0.clone();
      player1.printStateGame();
      Integer integer0 = new Integer(0);
      gameState0.gameBoard.put((Card) null, integer0);
      gameState0.isGameOver();
      gameState0.gameBoard.clear();
      gameState0.clone();
      gameState0.initializeGame();
  }

  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      List<Card> list0 = Card.createCards();
      GameState gameState0 = new GameState(list0);
      gameState0.getWinners();
      gameState0.initializeGame();
      HashMap<Card, Integer> hashMap0 = gameState0.gameBoard;
      Card.Type.values();
      gameState0.addPlayer((Player) null);
      gameState0.gameBoard = hashMap0;
      gameState0.addPlayer((Player) null);
      String string0 = "+";
      // Undeclared exception!
      gameState0.initializeGame();
  }

  @Test(timeout = 4000)
  public void test19()  throws Throwable  {
      Card.Type.values();
      LinkedList<Card> linkedList0 = new LinkedList<Card>();
      GameState gameState0 = new GameState((List<Card>) linkedList0);
      gameState0.getWinners();
      gameState0.initializeGame();
      HashMap<Card, Integer> hashMap0 = gameState0.gameBoard;
      Card.Type.values();
      gameState0.addPlayer((Player) null);
      gameState0.initializeGame();
      Card.CardName.values();
      HashMap<Card, Integer> hashMap1 = new HashMap<Card, Integer>();
      gameState0.gameBoard = hashMap1;
      Player player0 = new Player(gameState0, "");
      player0.drawCard();
      Integer integer0 = new Integer(0);
      hashMap1.put((Card) null, integer0);
      // Undeclared exception!
      try { 
        gameState0.toString();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.cs362.dominion.GameState", e);
      }
  }

  @Test(timeout = 4000)
  public void test20()  throws Throwable  {
      Stack<Card> stack0 = new Stack<Card>();
      GameState gameState0 = new GameState((List<Card>) stack0);
      gameState0.getWinners();
      Player player0 = new Player(gameState0, "the number of players mus be between 2 and 4 ");
      Player player1 = player0.clone();
      gameState0.addPlayer(player1);
      gameState0.initializeGame();
      stack0.clear();
      gameState0.play();
      gameState0.initializeGame();
      stack0.listIterator();
      Player player2 = new Player(gameState0, "the number of players mus be between 2 and 4 ");
      gameState0.addPlayer(player2);
      gameState0.addPlayer(player2);
      gameState0.addPlayer(player2);
      Player player3 = player2.clone();
      player3.printStateGame();
      gameState0.addPlayer(player3);
      gameState0.gameBoard.clear();
      gameState0.clone();
      gameState0.initializeGame();
      Card.Type.values();
      HashMap<Card, Integer> hashMap0 = gameState0.gameBoard;
      // Undeclared exception!
      try { 
        Card.CardName.valueOf("the number of players mus be between 2 and 4 ");
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // No enum constant org.cs362.dominion.Card.CardName.the number of players mus be between 2 and 4 
         //
         verifyException("java.lang.Enum", e);
      }
  }
}