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
	public void TestPlayerWin() throws DeckException, HandException {

		Table tble = new Table();
		Player player1 = new Player("Luke", 1);
		Player player2 = new Player("Alvin", 2);
		
		tble.AddPlayerToTable(player1);
		tble.AddPlayerToTable(player2);
		Deck dck = new Deck();
		GamePlayBlackJack gpBJ = new GamePlayBlackJack(tble.getHmTablePlayer(), dck);
		Iterator itt = gpBJ.getHmGameHands().entrySet().iterator();
		GamePlayerHand Test = null;
		HandBlackJack PH = null;
		while (itt.hasNext()) {
			Map.Entry pair = (Map.Entry) itt.next();
			HandBlackJack hBJ = (HandBlackJack) pair.getValue();
			GamePlayerHand GPH = (GamePlayerHand) pair.getKey();
			
			if (GPH.getPlayerID() == player1.getPlayerID()) {
				gpBJ.putHandToGame(GPH, hBJ);
				gpBJ.Draw(GPH, new Card(eSuit.DIAMONDS, eRank.NINE));
				gpBJ.Draw(GPH, new Card(eSuit.DIAMONDS, eRank.QUEEN));
				gpBJ.putHandToGame(GPH, hBJ);
				Test = GPH;
				
				PH = hBJ;
			}

			if (GPH.getPlayerID() == player2.getPlayerID()) {
				gpBJ.Draw(GPH, new Card(eSuit.CLUBS, eRank.FIVE));
				gpBJ.Draw(GPH, new Card(eSuit.CLUBS, eRank.FOUR));
				gpBJ.putHandToGame(GPH, hBJ);
			}

		}
		gpBJ.setDealerHand(new Card(eSuit.CLUBS, eRank.FIVE));
		gpBJ.setDealerHand(new Card(eSuit.CLUBS, eRank.SIX));
		
		gpBJ.ScoreGame(Test);
		assertEquals(PH.geteBJR(), eBlackJackResult.WIN);
	}

	@Test
	public void TestPlayerLosing() throws DeckException, HandException {
		
		Table tble = new Table();
		Player p1 = new Player("Luke", 1);
		Player p2 = new Player("Alvin", 2);
		tble.AddPlayerToTable(p1);
		tble.AddPlayerToTable(p2);
		Deck dck = new Deck();
		GamePlayBlackJack gpBJ = new GamePlayBlackJack(tble.getHmTablePlayer(), dck);
		Iterator itt = gpBJ.getHmGameHands().entrySet().iterator();
		GamePlayerHand Test = null;
		HandBlackJack PH = null;
		
		while (itt.hasNext()) {
			Map.Entry pair = (Map.Entry) itt.next();
			HandBlackJack hBJ = (HandBlackJack) pair.getValue();
			GamePlayerHand GPH = (GamePlayerHand) pair.getKey();
			
			if (GPH.getPlayerID() == p1.getPlayerID()) {
				gpBJ.putHandToGame(GPH, hBJ);
				gpBJ.Draw(GPH, new Card(eSuit.SPADES, eRank.TWO));
				gpBJ.Draw(GPH, new Card(eSuit.SPADES, eRank.THREE));
				gpBJ.putHandToGame(GPH, hBJ);
				Test = GPH;
				PH = hBJ;
			}

			if (GPH.getPlayerID() == p2.getPlayerID()) {
				
				gpBJ.Draw(GPH, new Card(eSuit.HEARTS, eRank.FIVE));
				gpBJ.Draw(GPH, new Card(eSuit.HEARTS, eRank.FOUR));
				gpBJ.putHandToGame(GPH, hBJ);
			}

		}
		gpBJ.setDealerHand(new Card(eSuit.HEARTS, eRank.FIVE));
		gpBJ.setDealerHand(new Card(eSuit.HEARTS, eRank.SIX));
		gpBJ.ScoreGame(Test);
		assertEquals(PH.geteBJR(), eBlackJackResult.LOSE);
		
	}

	@Test
	public void TestPlayerPush() throws DeckException, HandException {
		
		Table tble = new Table();
		Player p1 = new Player("Luke", 1);
		Player p2 = new Player("Alvin", 2);
		tble.AddPlayerToTable(p1);
		tble.AddPlayerToTable(p2);
		Deck dck = new Deck();
		GamePlayBlackJack gpBJ = new GamePlayBlackJack(tble.getHmTablePlayer(), dck);
		Iterator itt = gpBJ.getHmGameHands().entrySet().iterator();
		GamePlayerHand Test = null;
		HandBlackJack PH = null;
		
		while (itt.hasNext()) {
			Map.Entry pair = (Map.Entry) itt.next();
			HandBlackJack hBJ = (HandBlackJack) pair.getValue();
			GamePlayerHand GPH = (GamePlayerHand) pair.getKey();
			
			if (GPH.getPlayerID() == p1.getPlayerID()) {
				gpBJ.putHandToGame(GPH, hBJ);
				gpBJ.Draw(GPH, new Card(eSuit.DIAMONDS, eRank.TWO));
				gpBJ.Draw(GPH, new Card(eSuit.DIAMONDS, eRank.THREE));
				gpBJ.putHandToGame(GPH, hBJ);
				Test = GPH;
				PH = hBJ;
			}

			if (GPH.getPlayerID() == p2.getPlayerID()) {
				gpBJ.Draw(GPH, new Card(eSuit.DIAMONDS, eRank.FIVE));
				gpBJ.Draw(GPH, new Card(eSuit.DIAMONDS, eRank.FOUR));
				gpBJ.putHandToGame(GPH, hBJ);
			}

		}
			gpBJ.setDealerHand(new Card(eSuit.DIAMONDS, eRank.TWO));
			gpBJ.setDealerHand(new Card(eSuit.DIAMONDS, eRank.THREE));
			gpBJ.ScoreGame(Test);
			assertEquals(PH.geteBJR(), eBlackJackResult.TIE);
	}

	@Test
	public void TestTwoPlayersWinning() throws DeckException, HandException {
		
		Table tble = new Table();
		Player player1 = new Player("Mike", 1);
		Player player2 = new Player("John", 2);
		tble.AddPlayerToTable(player1);
		tble.AddPlayerToTable(player2);
		Deck dck = new Deck();
		GamePlayBlackJack gpBJ = new GamePlayBlackJack(tble.getHmTablePlayer(), dck);
		Iterator itt = gpBJ.getHmGameHands().entrySet().iterator();
		GamePlayerHand Test = null;
		GamePlayerHand Test2 = null;
		HandBlackJack PH = null;
		HandBlackJack JH = null;
		
		while (itt.hasNext()) {
			Map.Entry pair = (Map.Entry) itt.next();
			HandBlackJack hBJ = (HandBlackJack) pair.getValue();
			GamePlayerHand GPH = (GamePlayerHand) pair.getKey();
			
			if (GPH.getPlayerID() == player1.getPlayerID()) {
				gpBJ.putHandToGame(GPH, hBJ);
				gpBJ.Draw(GPH, new Card(eSuit.CLUBS, eRank.NINE));
				gpBJ.Draw(GPH, new Card(eSuit.CLUBS, eRank.QUEEN));
				gpBJ.putHandToGame(GPH, hBJ);
				Test = GPH;
				PH = hBJ;
			}

			if (GPH.getPlayerID() == player2.getPlayerID()) {
				gpBJ.Draw(GPH, new Card(eSuit.SPADES, eRank.TEN));
				gpBJ.Draw(GPH, new Card(eSuit.SPADES, eRank.KING));
				gpBJ.putHandToGame(GPH, hBJ);
				Test2 = GPH;
				JH = hBJ;
			}

		}
			gpBJ.setDealerHand(new Card(eSuit.HEARTS, eRank.FIVE));
			gpBJ.setDealerHand(new Card(eSuit.HEARTS, eRank.SIX));
			gpBJ.ScoreGame(Test);
			assertEquals(PH.geteBJR(), eBlackJackResult.WIN);
			gpBJ.ScoreGame(Test2);
			assertEquals(JH.geteBJR(), eBlackJackResult.WIN);
	}
}