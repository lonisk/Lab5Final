package pkgCore;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;

import pkgException.DeckException;
import pkgException.HandException;
import pkgEnum.eBlackJackResult;
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
		HandScoreBlackJack dScore = (HandScoreBlackJack) hDealer.getHS();
		
		Iterator it = this.getHmGameHands().entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			
			GamePlayerHand kGPH = (GamePlayerHand) pair.getKey();
			
			if (kGPH.getGameID() == GPH.getGameID())
			{
				HandBlackJack hPlayer = (HandBlackJack) pair.getValue();
				
				HandScoreBlackJack pHSP = null;
				
				pHSP = (HandScoreBlackJack) hPlayer.ScoreHand();
				
				eBlackJackResult pBJR = CheckScore(dScore,pHSP);
				
				hPlayer.seteBJR(pBJR);
				
				this.putHandToGame(kGPH, hPlayer);
				
			}

		}
		
	}

	private eBlackJackResult CheckScore(HandScoreBlackJack dHSB, HandScoreBlackJack pHSB)
	{
		eBlackJackResult eBS = eBlackJackResult.TIE;
		
		if (isBusted(pHSB))
		{
			return eBlackJackResult.LOSE;
		}
		
		if (isBusted(dHSB))
		{
			return eBlackJackResult.WIN;
		}
		
		if ((Integer)dHSB.getNumericScores().getLast() < (Integer)pHSB.getNumericScores().getLast())
		{
			return eBlackJackResult.WIN;
		}
		else if ((Integer)dHSB.getNumericScores().getLast() > (Integer)pHSB.getNumericScores().getLast())
		{
			return eBlackJackResult.LOSE;
		}
		
		return eBS;
		
	}
	
	
	private boolean isBusted(HandScoreBlackJack HSB)
	{
		boolean isBusted = true;
		
		for (Integer i: HSB.getNumericScores())
		{
			if (i <= 21)
			{
				isBusted = false;
				break;
			}
		}
		return isBusted;
		
	}
	
	
	
	public Player getpDealer() {
		return pDealer;
	}
}
