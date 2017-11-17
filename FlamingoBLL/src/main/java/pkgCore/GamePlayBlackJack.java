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
import pkgEnum.eRank;
import pkgEnum.eSuit;

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

	public void Draw(GamePlayerHand GPH, Card c) throws DeckException, HandException {

		if (bCanPlayerDraw(GPH)) {
			Hand h = this.gethmGameHand(GPH);

			h.AddCard(c);

			this.putHandToGame(GPH, h);

		}

	}

	public void setDealerHand(Card c) throws DeckException, HandException {

		Hand h = hDealer;

		h.AddCard(c);

		// this.putHandToGame(GPH, h);

	}

	private boolean bCanPlayerDraw(GamePlayerHand GPH) throws HandException {
		boolean bCanPlayerDraw = false;

		Hand h = this.gethmGameHand(GPH);
		HandScoreBlackJack HSB = (HandScoreBlackJack) h.ScoreHand();

		for (Integer i : HSB.getNumericScores()) {
			if (i <= 21) {
				bCanPlayerDraw = true;
				break;
			}

		}

		return bCanPlayerDraw;
	}

	public boolean bDoesDealerHaveToDraw() throws HandException {
		boolean bDoesDealerHaveToDraw = true;

		HandScoreBlackJack HSB = (HandScoreBlackJack) hDealer.ScoreHand();

		for (Integer i : HSB.getNumericScores()) {
			if ((i >= 17)) {
				bDoesDealerHaveToDraw = false;
				break;
			}

		}

		return bDoesDealerHaveToDraw;
	}

	public void ScoreGame(GamePlayerHand GPH) throws HandException {
		HandScoreBlackJack dScore = (HandScoreBlackJack) hDealer.getHS();
		if (dScore == null) {
			dScore = (HandScoreBlackJack) hDealer.ScoreHand();
		}

		Iterator it = this.getHmGameHands().entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();

			GamePlayerHand kGPH = (GamePlayerHand) pair.getKey();

			if (kGPH.getGameID() == GPH.getGameID()) {
				HandBlackJack hPlayer = (HandBlackJack) pair.getValue();

				HandScoreBlackJack pHSP = null;

				pHSP = (HandScoreBlackJack) hPlayer.ScoreHand();

				eBlackJackResult pBJR = CheckScore(dScore, pHSP);

				hPlayer.seteBJR(pBJR);

				this.putHandToGame(kGPH, hPlayer);

			}

		}

	}

	private eBlackJackResult CheckScore(HandScoreBlackJack dHSB, HandScoreBlackJack pHSB) {
		eBlackJackResult eBS = eBlackJackResult.TIE;

		if (isBusted(pHSB)) {
			return eBlackJackResult.LOSE;
		}

		if (isBusted(dHSB)) {
			return eBlackJackResult.WIN;
		}

		if ((Integer) dHSB.getNumericScores().getLast() < (Integer) pHSB.getNumericScores().getLast()) {
			return eBlackJackResult.WIN;
		} else if ((Integer) dHSB.getNumericScores().getLast() > (Integer) pHSB.getNumericScores().getLast()) {
			return eBlackJackResult.LOSE;
		}

		return eBS;

	}

	private boolean isBusted(HandScoreBlackJack HSB) {
		boolean isBusted = true;

		for (Integer i : HSB.getNumericScores()) {

			if (i <= 21) {
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
