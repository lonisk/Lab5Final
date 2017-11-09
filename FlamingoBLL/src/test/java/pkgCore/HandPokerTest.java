package pkgCore;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import pkgEnum.eHandStrength;
import pkgEnum.eRank;
import pkgEnum.eSuit;
import pkgException.HandException;

public class HandPokerTest {

	@Test
	public void FourOfAKindTest1() {
		System.out.println("Four of a Kind");
		System.out.println("");
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.TWO));
		hp.AddCard(new Card(eSuit.DIAMONDS,eRank.TWO));
		hp.AddCard(new Card(eSuit.SPADES,eRank.TWO));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.THREE));
		try {
			hp.ScoreHand();
			assertEquals(eHandStrength.FourOfAKind,hp.getHandScorePoker().geteHandStrength());
			assertEquals(eRank.TWO,hp.getHandScorePoker().getHiCard().geteRank());
			assertNull(hp.getHandScorePoker().getLoCard());
			assertEquals(eRank.THREE,hp.getHandScorePoker().getKickers().get(0).geteRank());			
		} catch (HandException e) {
			e.printStackTrace();
			fail();
		}
		System.out.println("");
	}
	
	@Test
	public void ThreeOfAKindTest1() {
		System.out.println("Three of a Kind");
		System.out.println("");
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.TWO));
		hp.AddCard(new Card(eSuit.DIAMONDS,eRank.TWO));
		hp.AddCard(new Card(eSuit.SPADES,eRank.FIVE));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.THREE));
		try {
			hp.ScoreHand();
			assertEquals(eHandStrength.ThreeOfAKind,hp.getHandScorePoker().geteHandStrength());
			assertEquals(eRank.TWO,hp.getHandScorePoker().getHiCard().geteRank());
			assertNull(hp.getHandScorePoker().getLoCard());
			assertEquals(eRank.FIVE,hp.getHandScorePoker().getKickers().get(0).geteRank());
			assertEquals(eRank.THREE,hp.getHandScorePoker().getKickers().get(1).geteRank());
		} catch (HandException e) {
			e.printStackTrace();
			fail();
		}
		System.out.println("");
	}
	
	@Test
	public void TwoPairTest1() {
		System.out.println("Two Pair");
		System.out.println("");
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.TWO));
		hp.AddCard(new Card(eSuit.DIAMONDS,eRank.FIVE));
		hp.AddCard(new Card(eSuit.SPADES,eRank.FIVE));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.THREE));
		try {
			hp.ScoreHand();
			assertEquals(eHandStrength.TwoPair,hp.getHandScorePoker().geteHandStrength());
			assertEquals(eRank.FIVE,hp.getHandScorePoker().getHiCard().geteRank());			
			assertEquals(eRank.TWO,hp.getHandScorePoker().getLoCard().geteRank());			
			assertEquals(eRank.THREE,hp.getHandScorePoker().getKickers().get(0).geteRank());			
		} catch (HandException e) {
			e.printStackTrace();
			fail();
		}
		System.out.println("");
	}
	
	@Test
	public void FullHouseTest1() {
		System.out.println("Full House");
		System.out.println("");
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.TWO));
		hp.AddCard(new Card(eSuit.DIAMONDS,eRank.FIVE));
		hp.AddCard(new Card(eSuit.SPADES,eRank.FIVE));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.FIVE));
		try {
			hp.ScoreHand();
			assertEquals(eHandStrength.FullHouse,hp.getHandScorePoker().geteHandStrength());
			assertEquals(eRank.FIVE,hp.getHandScorePoker().getHiCard().geteRank());			
			assertEquals(eRank.TWO,hp.getHandScorePoker().getLoCard().geteRank());				
		} catch (HandException e) {
			e.printStackTrace();
			fail();
		}
		System.out.println("");
	}
	
	
	@Test
	public void PairTest1() {
		System.out.println("Pair");
		System.out.println("");
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hp.AddCard(new Card(eSuit.HEARTS,eRank.TWO));
		hp.AddCard(new Card(eSuit.DIAMONDS,eRank.THREE));
		hp.AddCard(new Card(eSuit.SPADES,eRank.FOUR));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.FIVE));
		try {
			hp.ScoreHand();
			assertEquals(eHandStrength.Pair,hp.getHandScorePoker().geteHandStrength());
			assertEquals(eRank.TWO,hp.getHandScorePoker().getHiCard().geteRank());
			assertNull(hp.getHandScorePoker().getLoCard());
			assertEquals(eRank.FIVE,hp.getHandScorePoker().getKickers().get(0).geteRank());
			assertEquals(eRank.FOUR,hp.getHandScorePoker().getKickers().get(1).geteRank());
			assertEquals(eRank.THREE,hp.getHandScorePoker().getKickers().get(2).geteRank());

		} catch (HandException e) {
			e.printStackTrace();
			fail();
		}
		System.out.println("");
	}

	
	@Test
	public void FlushTest1() {
		System.out.println("Flush");
		System.out.println("");
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.THREE));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.FOUR));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.FIVE));
		try {
			hp.ScoreHand();
			assertEquals(eHandStrength.Flush,hp.getHandScorePoker().geteHandStrength());
			assertEquals(eRank.TWO,hp.getHandScorePoker().getHiCard().geteRank());
			assertEquals(eSuit.CLUBS,hp.getHandScorePoker().getHiCard().geteSuit());
			assertNull(hp.getHandScorePoker().getLoCard());
			assertEquals(eRank.FIVE,hp.getHandScorePoker().getKickers().get(0).geteRank());
			assertEquals(eRank.FOUR,hp.getHandScorePoker().getKickers().get(1).geteRank());
			assertEquals(eRank.THREE,hp.getHandScorePoker().getKickers().get(2).geteRank());

		} catch (HandException e) {
			e.printStackTrace();
			fail();
		}
		System.out.println("");
	}
	
	@Test
	public void StraightTest1() {
		System.out.println("Straight");
		System.out.println("");
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.THREE));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.FOUR));
		hp.AddCard(new Card(eSuit.SPADES,eRank.FIVE));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.SIX));
		try {
			hp.ScoreHand();
			assertEquals(eHandStrength.Straight,hp.getHandScorePoker().geteHandStrength());
			assertEquals(eRank.SIX,hp.getHandScorePoker().getHiCard().geteRank());
			assertEquals(eSuit.CLUBS,hp.getHandScorePoker().getHiCard().geteSuit());

		} catch (HandException e) {
			e.printStackTrace();
			fail();
		}
		System.out.println("");
	}
	
	@Test
	public void StraightTest2() {
		System.out.println("Straight");
		System.out.println("");
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS,eRank.TEN));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.JACK));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.QUEEN));
		hp.AddCard(new Card(eSuit.SPADES,eRank.KING));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.ACE));
		try {
			hp.ScoreHand();
			assertEquals(eHandStrength.Straight,hp.getHandScorePoker().geteHandStrength());
			assertEquals(eRank.ACE,hp.getHandScorePoker().getHiCard().geteRank());
			assertEquals(eSuit.CLUBS,hp.getHandScorePoker().getHiCard().geteSuit());

		} catch (HandException e) {
			e.printStackTrace();
			fail();
		}
		System.out.println("");
	}
	
	@Test
	public void StraightTest3() {
		System.out.println("Straight");
		System.out.println("");
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS,eRank.ACE));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.THREE));
		hp.AddCard(new Card(eSuit.SPADES,eRank.FOUR));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.FIVE));
		try {
			hp.ScoreHand();
			assertEquals(eHandStrength.Straight,hp.getHandScorePoker().geteHandStrength());
			assertEquals(eRank.FIVE,hp.getHandScorePoker().getHiCard().geteRank());
			assertEquals(eSuit.CLUBS,hp.getHandScorePoker().getHiCard().geteSuit());

		} catch (HandException e) {
			e.printStackTrace();
			fail();
		}
		System.out.println("");
	}
	
	
	@Test
	public void StraightTestFlush1() {
		System.out.println("Straight Flush");
		System.out.println("");
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS,eRank.ACE));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.TWO));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.THREE));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.FOUR));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.FIVE));
		try {
			hp.ScoreHand();
			assertEquals(eHandStrength.StraightFlush,hp.getHandScorePoker().geteHandStrength());
			assertEquals(eRank.FIVE,hp.getHandScorePoker().getHiCard().geteRank());
			assertEquals(eSuit.CLUBS,hp.getHandScorePoker().getHiCard().geteSuit());

		} catch (HandException e) {
			e.printStackTrace();
			fail();
		}
		System.out.println("");
	}
	
	
	@Test
	public void RoyalFlushTest1() {
		System.out.println("Royal Flush");
		System.out.println("");
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS,eRank.ACE));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.KING));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.QUEEN));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.JACK));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.TEN));
		try {
			hp.ScoreHand();
			assertEquals(eHandStrength.RoyalFlush,hp.getHandScorePoker().geteHandStrength());
			assertEquals(eRank.ACE,hp.getHandScorePoker().getHiCard().geteRank());
			assertEquals(eSuit.CLUBS,hp.getHandScorePoker().getHiCard().geteSuit());

		} catch (HandException e) {
			e.printStackTrace();
			fail();
		}
		System.out.println("");
	}
	
	
	
	@Test
	public void HighCardTest1() {
		System.out.println("High Card");
		System.out.println("");
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS,eRank.ACE));
		hp.AddCard(new Card(eSuit.DIAMONDS,eRank.KING));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.FOUR));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.JACK));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.TEN));
		try {
			hp.ScoreHand();
			assertEquals(eHandStrength.HighCard,hp.getHandScorePoker().geteHandStrength());
			assertEquals(eRank.ACE,hp.getHandScorePoker().getHiCard().geteRank());
			assertEquals(eSuit.CLUBS,hp.getHandScorePoker().getHiCard().geteSuit());
			assertEquals(4,hp.getHandScorePoker().getKickers().size());
		} catch (HandException e) {
			e.printStackTrace();
			fail();
		}
		System.out.println("");
	}
	
	
	
	@Test
	public void CompareHands1() {
		System.out.println("High Card");
		System.out.println("");
		HandPoker hp = new HandPoker();
		hp.AddCard(new Card(eSuit.CLUBS,eRank.ACE));
		hp.AddCard(new Card(eSuit.DIAMONDS,eRank.ACE));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.FOUR));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.JACK));
		hp.AddCard(new Card(eSuit.CLUBS,eRank.TEN));
		
		HandPoker hp2 = new HandPoker();
		hp2.AddCard(new Card(eSuit.CLUBS,eRank.ACE));
		hp2.AddCard(new Card(eSuit.DIAMONDS,eRank.ACE));
		hp2.AddCard(new Card(eSuit.CLUBS,eRank.ACE));
		hp2.AddCard(new Card(eSuit.CLUBS,eRank.JACK));
		hp2.AddCard(new Card(eSuit.CLUBS,eRank.TEN));		
		

		
		
		
		
		try {
			hp.ScoreHand();
			hp2.ScoreHand();
			ArrayList<HandPoker> hands = new ArrayList<HandPoker>();
			hands.add(hp);
			hands.add(hp2);
			
			Collections.sort(hands);
			
			assertEquals(eHandStrength.Pair,hp.getHandScorePoker().geteHandStrength());
			assertEquals(eRank.ACE,hp.getHandScorePoker().getHiCard().geteRank());
			assertEquals(eSuit.CLUBS,hp.getHandScorePoker().getHiCard().geteSuit());
			assertEquals(3,hp.getHandScorePoker().getKickers().size());
			
			HandPoker pk1 = hands.get(0);
			
			assertEquals(eHandStrength.ThreeOfAKind,pk1.getHandScorePoker().geteHandStrength());
			
			
		
		
		
		} catch (HandException e) {
			e.printStackTrace();
			fail();
		}
		System.out.println("");
	}
	
	
}
