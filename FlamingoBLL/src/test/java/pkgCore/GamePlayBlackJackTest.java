package pkgCore;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;

import pkgEnum.eBlackJackResult;
import pkgEnum.eRank;
import pkgEnum.eSuit;
import pkgException.DeckException;
import pkgException.HandException;

public class GamePlayBlackJackTest {

	@Test
	public void TestPlayerWinning() throws DeckException, HandException {

		Table t = new Table();
		Player p1 = new Player("Mike", 1);
		Player p2 = new Player("John", 2);
		t.AddPlayerToTable(p1);
		t.AddPlayerToTable(p2);
		Deck d = new Deck();
		GamePlayBlackJack gpBJ = new GamePlayBlackJack(t.getHmTablePlayer(), d);
		Iterator it = gpBJ.getHmGameHands().entrySet().iterator();
		GamePlayerHand Test = null;
		HandBlackJack PH = null;
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			HandBlackJack hBJ = (HandBlackJack) pair.getValue();
			GamePlayerHand GPH = (GamePlayerHand) pair.getKey();
			
			if (GPH.getPlayerID() == p1.getPlayerID()) {
				
				gpBJ.putHandToGame(GPH, hBJ);
				gpBJ.Draw(GPH, new Card(eSuit.HEARTS, eRank.NINE));
				gpBJ.Draw(GPH, new Card(eSuit.HEARTS, eRank.QUEEN));
				gpBJ.putHandToGame(GPH, hBJ);
				Test = GPH;
				
				PH = hBJ;
			}

			if (GPH.getPlayerID() == p2.getPlayerID()) {
				
				gpBJ.Draw(GPH, new Card(eSuit.SPADES, eRank.FIVE));
				gpBJ.Draw(GPH, new Card(eSuit.SPADES, eRank.FOUR));
				gpBJ.putHandToGame(GPH, hBJ);
			}


		}
		gpBJ.setDealerHand(new Card(eSuit.SPADES, eRank.FIVE));
		gpBJ.setDealerHand(new Card(eSuit.SPADES, eRank.SIX));
		
		gpBJ.ScoreGame(Test);
		assertEquals(PH.geteBJR(), eBlackJackResult.WIN);
	}

	
	
	
	@Test
	public void TestPlayerLosing() throws DeckException, HandException {
		
		Table t = new Table();
		Player p1 = new Player("Mike", 1);
		Player p2 = new Player("John", 2);
		t.AddPlayerToTable(p1);
		t.AddPlayerToTable(p2);
		Deck d = new Deck();
		GamePlayBlackJack gpBJ = new GamePlayBlackJack(t.getHmTablePlayer(), d);
		Iterator it = gpBJ.getHmGameHands().entrySet().iterator();
		GamePlayerHand Test = null;
		HandBlackJack PH = null;
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			HandBlackJack hBJ = (HandBlackJack) pair.getValue();
			GamePlayerHand GPH = (GamePlayerHand) pair.getKey();
			
			if (GPH.getPlayerID() == p1.getPlayerID()) {
				
				gpBJ.putHandToGame(GPH, hBJ);
				gpBJ.Draw(GPH, new Card(eSuit.HEARTS, eRank.TWO));
				gpBJ.Draw(GPH, new Card(eSuit.HEARTS, eRank.THREE));
				gpBJ.putHandToGame(GPH, hBJ);
				Test = GPH;
				
				PH = hBJ;
			}

			if (GPH.getPlayerID() == p2.getPlayerID()) {
				
				gpBJ.Draw(GPH, new Card(eSuit.SPADES, eRank.FIVE));
				gpBJ.Draw(GPH, new Card(eSuit.SPADES, eRank.FOUR));
				gpBJ.putHandToGame(GPH, hBJ);
			}

			
			

		}
		gpBJ.setDealerHand(new Card(eSuit.SPADES, eRank.FIVE));
		gpBJ.setDealerHand(new Card(eSuit.SPADES, eRank.SIX));
		
		gpBJ.ScoreGame(Test);
		assertEquals(PH.geteBJR(), eBlackJackResult.LOSE);
		
		
		

	}

	@Test
	public void TestPlayerPush() throws DeckException, HandException {
		
		Table t = new Table();
		Player p1 = new Player("Mike", 1);
		Player p2 = new Player("John", 2);
		t.AddPlayerToTable(p1);
		t.AddPlayerToTable(p2);
		Deck d = new Deck();
		GamePlayBlackJack gpBJ = new GamePlayBlackJack(t.getHmTablePlayer(), d);
		Iterator it = gpBJ.getHmGameHands().entrySet().iterator();
		GamePlayerHand Test = null;
		HandBlackJack PH = null;
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			HandBlackJack hBJ = (HandBlackJack) pair.getValue();
			GamePlayerHand GPH = (GamePlayerHand) pair.getKey();
			
			if (GPH.getPlayerID() == p1.getPlayerID()) {
				
				gpBJ.putHandToGame(GPH, hBJ);
				gpBJ.Draw(GPH, new Card(eSuit.HEARTS, eRank.TWO));
				gpBJ.Draw(GPH, new Card(eSuit.HEARTS, eRank.THREE));
				gpBJ.putHandToGame(GPH, hBJ);
				Test = GPH;
				
				PH = hBJ;
			}

			if (GPH.getPlayerID() == p2.getPlayerID()) {
				
				gpBJ.Draw(GPH, new Card(eSuit.SPADES, eRank.FIVE));
				gpBJ.Draw(GPH, new Card(eSuit.SPADES, eRank.FOUR));
				gpBJ.putHandToGame(GPH, hBJ);
			}

			
			

		}
		gpBJ.setDealerHand(new Card(eSuit.SPADES, eRank.TWO));
		gpBJ.setDealerHand(new Card(eSuit.SPADES, eRank.THREE));
		
		gpBJ.ScoreGame(Test);
		assertEquals(PH.geteBJR(), eBlackJackResult.TIE);
		
		
		

	}

	@Test
	public void TestTwoPlayersWinning() throws DeckException, HandException {
		
		
		Table t = new Table();
		Player p1 = new Player("Mike", 1);
		Player p2 = new Player("John", 2);
		t.AddPlayerToTable(p1);
		t.AddPlayerToTable(p2);
		Deck d = new Deck();
		GamePlayBlackJack gpBJ = new GamePlayBlackJack(t.getHmTablePlayer(), d);
		Iterator it = gpBJ.getHmGameHands().entrySet().iterator();
		GamePlayerHand Test = null;
		GamePlayerHand Test2 = null;
		HandBlackJack PH = null;
		HandBlackJack JH = null;
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			HandBlackJack hBJ = (HandBlackJack) pair.getValue();
			GamePlayerHand GPH = (GamePlayerHand) pair.getKey();
			
			if (GPH.getPlayerID() == p1.getPlayerID()) {
				
				gpBJ.putHandToGame(GPH, hBJ);
				gpBJ.Draw(GPH, new Card(eSuit.HEARTS, eRank.NINE));
				gpBJ.Draw(GPH, new Card(eSuit.HEARTS, eRank.QUEEN));
				gpBJ.putHandToGame(GPH, hBJ);
				Test = GPH;
				
				PH = hBJ;
			}

			if (GPH.getPlayerID() == p2.getPlayerID()) {
				
				gpBJ.Draw(GPH, new Card(eSuit.SPADES, eRank.TEN));
				gpBJ.Draw(GPH, new Card(eSuit.SPADES, eRank.KING));
				gpBJ.putHandToGame(GPH, hBJ);
				
				Test2 = GPH;
				JH = hBJ;
			}

			
			

		}
		gpBJ.setDealerHand(new Card(eSuit.SPADES, eRank.FIVE));
		gpBJ.setDealerHand(new Card(eSuit.SPADES, eRank.SIX));
		
		gpBJ.ScoreGame(Test);
		assertEquals(PH.geteBJR(), eBlackJackResult.WIN);
		
		gpBJ.ScoreGame(Test2);
		assertEquals(JH.geteBJR(), eBlackJackResult.WIN);
		
		
		

	}
}
