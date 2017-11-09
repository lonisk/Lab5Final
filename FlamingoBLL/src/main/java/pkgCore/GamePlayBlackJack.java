package pkgCore;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;

import pkgException.DeckException;
import pkgException.HandException;

import pkgEnum.eGameType;

public class GamePlayBlackJack extends GamePlay {

	private Player pDealer = new Player("Dealer", 0);
	private Hand hDealer = new HandBlackJack();
	
	
	public GamePlayBlackJack(HashMap<UUID, Player> hmTablePlayers, Deck dGameDeck) {
	
		super(eGameType.BLACKJACK, hmTablePlayers, dGameDeck);	
		
		Iterator it = hmTablePlayers.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			Player p = (Player) pair.getValue();
			Hand h = new HandBlackJack();
			GamePlayerHand GPH = new GamePlayerHand(this.getGameID(), p.getPlayerID(), h.getHandID());
			this.putHandToGame(GPH, h);
		}
	}

	@Override
	protected Card Draw(GamePlayerHand GPH) throws DeckException, HandException {

		Card c = null;

		if (bCanPlayerDraw(GPH)) {
			Hand h = this.gethmGameHand(GPH);
			c = h.Draw(this.getdGameDeck());
			
			h.AddCard(c);
			
			this.putHandToGame(GPH, h);

		}
		return c;
	}

	private boolean bCanPlayerDraw(GamePlayerHand GPH) throws HandException {
		boolean bCanPlayerDraw = false;

		Hand h = this.gethmGameHand(GPH);

		HandScoreBlackJack HSB = (HandScoreBlackJack)h.ScoreHand();
		
		for(Integer i: HSB.getNumericScores()) {
			if (i<=21) {
				bCanPlayerDraw = true;
				break;
			}
			
		}
		
		
		return bCanPlayerDraw;
	}
	
	
	
	public boolean bDoesDealerHaveToDraw() throws HandException
	{
		boolean bDoesDealerHaveToDraw = true;
		
		
		HandScoreBlackJack HSB = (HandScoreBlackJack)hDealer.ScoreHand();
		
		
		for(Integer i: HSB.getNumericScores()) {
			if ((i>=17)) {
				bDoesDealerHaveToDraw = false;
				break;
			}
			
		}
		
		
		return bDoesDealerHaveToDraw;
	}
	
	
	
	
	
	
	
	public void ScoreGame(GamePlayerHand GPH)
	{
		boolean bIsHandWinner = false;
		//	TODO: Determine if player is a winner
		
		
		HandScoreBlackJack dScore = (HandScoreBlackJack) hDealer.getHS();
		
		HandBlackJack pHand = (HandBlackJack) this.gethmGameHand(GPH);
		
		HandScoreBlackJack pScore = (HandScoreBlackJack) pHand.getHS();
		
		LinkedList pNums = pScore.getNumericScores();
		LinkedList dNums = dScore.getNumericScores();
		
		for(Object o :pNums) {
			for (Object q :dNums) {
				
				if(((((int) o <= 21))&&((int) o > (int) q))||(((int) o <= 21)&&((int) q >= 21))){
					bIsHandWinner = true;
					break;
				}
				
				
			}
			
			
		}
		
		//	TODO: If Player's hand > Dealer's hand and <= 21, then eBlackJackResult = WIN
		//			If Player's hand < Dealer's hand and Dealer didn't bust = LOSE
		//			If Player's hand == Dealer's hand and both didn't bust = TIE
		
		
	}

	public Player getpDealer() {
		return pDealer;
	}
}
